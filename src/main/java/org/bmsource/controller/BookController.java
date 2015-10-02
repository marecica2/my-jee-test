package org.bmsource.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.bmsource.model.Book;
import org.bmsource.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@PreAuthorize("isAuthenticated()")
@Controller
public class BookController {

	@Inject
	SmartValidator validator;

	@Inject
	BookService bookService;

	@Inject
	HttpServletRequest request;

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "edit", required = false) Long edit, ModelMap model) {
		List<Book> books = bookService.getBooks();

		System.err.println(request.getSession().getAttributeNames());
		System.err.println(request.getRemoteUser());

		model.put("books", books);
		model.put("edit", edit);
		if (edit != null) {
			Book book = bookService.get(edit);
			model.addAttribute(book);
		} else {
			model.addAttribute(new Book());
		}
		return new ModelAndView("books", model);
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(ModelMap model) {
		List<Book> books = bookService.getBooks();
		model.put("books", books);
		model.put("edit", false);
		return new ModelAndView("admin", model);
	}

	@RequestMapping(value = "/book/add", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("book") Book book, BindingResult result, ModelMap model) {
		System.err.println(book);

		validator.validate(book, result);

		if (result.hasErrors()) {
			List<Book> books = bookService.getBooks();
			model.put("books", books);
			return new ModelAndView("books", model);
		}
		bookService.save(book);
		return new ModelAndView("redirect:/", model);
	}

	@RequestMapping(value = "/book/edit", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("book") Book book, BindingResult result, ModelMap model) {
		validator.validate(book, result);

		if (result.hasErrors()) {
			List<Book> books = bookService.getBooks();
			model.put("books", books);
			return new ModelAndView("books", model);
		}
		bookService.update(book);
		return new ModelAndView("redirect:/", model);
	}

	@RequestMapping(value = "/book/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("id") Long id, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
		}
		Book book = bookService.get(id);
		bookService.delete(book);
		return new ModelAndView("redirect:/", model);
	}

}
