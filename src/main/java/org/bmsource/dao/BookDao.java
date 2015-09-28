package org.bmsource.dao;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Singleton;

import org.bmsource.model.Book;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Singleton
@Named
public class BookDao extends GenericDao<Book, Long> {

	@Override
	@PostConstruct
	public void init() {
		System.err.println("bookdao");
	}
}
