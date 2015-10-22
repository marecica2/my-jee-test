package org.bmsource.service;

import java.util.List;

import org.bmsource.model.a.Book;
import org.bmsource.model.b.Group;
import org.springframework.validation.BindingResult;

public interface BookService {

	List<Book> getBooks();

	Book get(Long id);

	Book save(Book book, Group group);

	Book updateLock(Book book);

	Book update(Book book);

	Book save(Book book);

	void delete(Book book);

	BindingResult validate(BindingResult errors);

}