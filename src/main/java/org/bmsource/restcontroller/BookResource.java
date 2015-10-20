package org.bmsource.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.bmsource.model.a.Book;
import org.bmsource.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@PreAuthorize("isAuthenticated()")
@RequestMapping("/resources")
@Controller
public class BookResource {

	@Inject
	BookService bookService;

	@Inject
	HttpServletRequest request;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> books() throws Exception {
		List<Book> books = bookService.getBooks();
		boolean t = true;
		if (t == true)
			throw new IOException("Error accessing dao");
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

}