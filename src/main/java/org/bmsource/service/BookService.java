package org.bmsource.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bmsource.dao.BookDao;
import org.bmsource.model.Book;
import org.springframework.stereotype.Component;

@Singleton
@Component
public class BookService {
	@Inject
	BookDao bookDao;

	public List<Book> getBooks() {
		List<Book> books = bookDao.findAll();
		return books;
	}

	public Book get(Long id) {
		return bookDao.find(id);
	}

	public Book save(Book book) {
		return bookDao.create(book);
	}

	public Book update(Book book) {
		return bookDao.update(book);
	}

	public void delete(Book book) {
		bookDao.delete(book);
	}
}
