package org.bmsource.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.bmsource.model.a.Book;
import org.bmsource.service.BookService;

@WebService(serviceName = "BookService")
public class BookWsResource {

	@Inject
	BookService bookService;

	@Inject
	HttpServletRequest request;

	@WebMethod
	public List<Book> books() {
		List<Book> books = new ArrayList<>();
		Book e = new Book();
		e.setTitle("Title");
		e.setPages(10);
		e.setAuthor("Author");
		books.add(e);
		System.err.println(books);
		return books;
	}

}