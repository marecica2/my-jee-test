package org.bmsource.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.bmsource.model.a.Book;
import org.bmsource.model.a.BookType;
import org.bmsource.model.b.Group;
import org.bmsource.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@PreAuthorize("isAuthenticated()")
@Controller
public class BookController {

	@Inject
	Validator validator;

	@Inject
	BookService bookService;

	@Inject
	HttpServletRequest request;

	@PreAuthorize("permitAll")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome(ModelMap model) {
		return new ModelAndView("welcome", model);
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(ModelMap model) {
		List<Book> books = bookService.getBooks();
		model.put("books", books);
		model.put("edit", false);
		return new ModelAndView("admin", model);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ModelAndView books(@RequestParam(value = "edit", required = false) Long edit, ModelMap model, RedirectAttributes redirectAttributes) {
		List<Book> books = bookService.getBooks();
		model.put("books", books);
		model.put("edit", edit);
		if (edit != null) {
			Book book = bookService.get(edit);
			model.addAttribute(book);
		} else {
			Book book = new Book();
			book.setPages(10);
			book.setBookType(BookType.POETRY);
			model.addAttribute(book);
		}
		return new ModelAndView("books", model);
	}

	@RequestMapping(value = "/book/{action}", method = RequestMethod.POST)
	public ModelAndView save(@PathVariable String action, @ModelAttribute("book") Book bookForm,
			BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
		validator.validate(bookForm, result);
		bookService.validate(result);
		if (result.hasErrors()) {
			List<Book> books = bookService.getBooks();
			model.put("books", books);
			return new ModelAndView("books", model);
		}
		try {

			if (action.equals("save")) {
				Group g = new Group();
				g.setName(System.currentTimeMillis() + "");
				bookService.save(bookForm, g);
			}
			if (action.equals("edit")) {
				bookService.update(bookForm);
			}
			redirectAttributes.addFlashAttribute("info", "Save success");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "Error occured" + e.getMessage());
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/books");
		return modelAndView;
	}

	@RequestMapping(value = "/book/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("id") Long id, BindingResult result, ModelMap model) {
		Book book = bookService.get(id);
		bookService.delete(book);
		return new ModelAndView("redirect:/books", model);
	}

}
