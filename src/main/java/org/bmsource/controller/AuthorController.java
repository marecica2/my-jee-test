package org.bmsource.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.bmsource.dao.GroupDao;
import org.bmsource.model.a.Author;
import org.bmsource.model.a.Book;
import org.bmsource.service.AuthorService;
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
public class AuthorController {

	@Inject
	SmartValidator validator;

	@Inject
	AuthorService authorService;

	@Inject
	BookService bookService;

	@Inject
	GroupDao groupDao;

	@Inject
	HttpServletRequest request;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "edit", required = false) Long edit, ModelMap model) {
		List<Author> authors = authorService.getAuthors();
		List<Book> books = bookService.getBooks();
		model.put("authors", authors);
		model.put("books", books);
		model.put("edit", edit);
		if (edit != null) {
			Author author = authorService.get(edit);
			model.addAttribute(author);
			model.put("author", author);
		} else {
			Author author = new Author();
			model.addAttribute(author);
		}
		Author filter = new Author();
		filter.setFirstName("Marek");
		List<Author> filtered = authorService.getFiltered(filter);
		System.err.println(filtered);
		return new ModelAndView("authors", model);
	}

	@RequestMapping(value = "/author/add", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("author") Author author, BindingResult result, ModelMap model) {
		validator.validate(author, result);
		if (result.hasErrors()) {
			List<Author> authors = authorService.getAuthors();
			model.put("authors", authors);
			return new ModelAndView("authors", model);
		}
		authorService.save(author);
		return new ModelAndView("redirect:/authors", model);
	}

	@RequestMapping(value = "/author/edit", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("author") Author authorForm, BindingResult result, ModelMap model) {
		validator.validate(authorForm, result);
		if (result.hasErrors()) {
			List<Author> authors = authorService.getAuthors();
			model.put("authors", authors);
			return new ModelAndView("authors", model);
		}
		Author author = authorService.get(authorForm.getId());
		author.setFirstName(authorForm.getFirstName());
		author.setLastName(authorForm.getLastName());
		authorService.update(author);
		return new ModelAndView("redirect:/authors", model);
	}

	@RequestMapping(value = "/author/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("id") Long id, BindingResult result, ModelMap model) {
		Author author = authorService.get(id);
		authorService.delete(author);
		return new ModelAndView("redirect:/authors", model);
	}

	@RequestMapping(value = "/author/book/add", method = RequestMethod.POST)
	public ModelAndView addBook(@ModelAttribute("book") Long bookId, @ModelAttribute("author") Long authorId, BindingResult result, ModelMap model) {
		Author author = authorService.get(authorId);
		Book book = bookService.get(bookId);
		author.getBooks().add(book);
		book.getAuthors().add(author);
		authorService.update(author);
		bookService.update(book);
		return new ModelAndView("redirect:/authors", model);
	}

}
