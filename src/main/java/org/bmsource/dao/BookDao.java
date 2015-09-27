package org.bmsource.dao;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.bmsource.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Singleton
@Component
public class BookDao extends GenericDao<Book, Long> {

	@Override
	@PostConstruct
	public void init() {
		System.err.println("bookdao");
	}
}
