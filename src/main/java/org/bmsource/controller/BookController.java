package org.bmsource.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.bmsource.dao.GroupDao;
import org.bmsource.model.a.Book;
import org.bmsource.model.b.Group;
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
	GroupDao groupDao;

	@Inject
	HttpServletRequest request;

	@PreAuthorize("permitAll")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome(ModelMap model) {
		return new ModelAndView("welcome", model);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "edit", required = false) Long edit, ModelMap model) {
		List<Book> books = bookService.getBooks();
		model.put("books", books);
		model.put("edit", edit);
		if (edit != null) {
			Book book = bookService.get(edit);
			model.addAttribute(book);
		} else {
			Book book = new Book();
			book.setPages(10);
			model.addAttribute(book);
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
		validator.validate(book, result);
		if (result.hasErrors()) {
			List<Book> books = bookService.getBooks();
			model.put("books", books);
			return new ModelAndView("books", model);
		}
		bookService.save(book);

		Group g = new Group();
		g.setName("Test");
		groupDao.create(g);

		return new ModelAndView("redirect:/books", model);
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
		return new ModelAndView("redirect:/books", model);
	}

	@RequestMapping(value = "/book/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("id") Long id, BindingResult result, ModelMap model) {
		Book book = bookService.get(id);
		bookService.delete(book);
		return new ModelAndView("redirect:/books", model);
	}

}
