package org.bmsource.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bmsource.controller.UserSession;
import org.bmsource.dao.BookDao;
import org.bmsource.model.a.Book;
import org.bmsource.model.b.Group;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.BindingResult;

@Singleton
@Component
@Transactional
public class BookServiceImpl implements BookService {

	@Inject
	BookDao bookDao;

	@Inject
	UserSession us;

	@Inject
	GroupService groupService;

	@Inject
	private PlatformTransactionManager transactionManager;

	final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

	@Override
	public List<Book> getBooks() {
		List<Book> books = bookDao.findAll();
		return books;
	}

	@Override
	public Book get(Long id) {
		return bookDao.findLock(id);
	}

	@Override
	public Book save(Book book, Group group) {
		groupService.save(group);
		return bookDao.save(book);
	}

	@Override
	public Book updateLock(Book book) {
		// User user = us.getUser();
		// if (user.getLogin().equals("x")) {
		// try {
		// Thread.sleep(6000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		return update(book);
	}

	@Override
	public Book update(Book book) {
		book = bookDao.save(book);
		return book;
	}

	@Override
	public Book save(Book book) {
		book = bookDao.save(book);
		return book;
	}

	@Override
	public void delete(Book book) {
		bookDao.delete(book);
	}

	@Override
	public BindingResult validate(BindingResult errors) {
		// errors.addError(new FieldError("book", "title", "sasasasa"));
		return errors;
	}
}
