package org.bmsource.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.bmsource.model.Book;
import org.bmsource.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
	@Inject
	BookService bookService;

	@ModelAttribute("bookForm")
	public Book getBook() {
		return new Book();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelMap model) {
		List<Book> books = bookService.getBooks();
		model.put("books", books);
		return new ModelAndView("books", model);
	}

	@RequestMapping(value = "/book/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid @ModelAttribute("bookForm") Book bookForm, BindingResult result, ModelMap model) {
		System.err.println(bookForm);
		if (result.hasErrors()) {
			List<Book> books = bookService.getBooks();
			model.put("books", books);
			return new ModelAndView("books", model);
		}
		bookService.save(bookForm);
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
