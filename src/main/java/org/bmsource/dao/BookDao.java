package org.bmsource.dao;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bmsource.model.a.Book;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

@Singleton
@Component
public class BookDao extends GenericDao<Book, Long> {

	@PersistenceContext(unitName = "PersistenceUnitA")
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public Book findEager(Long id) {
		Book book = getEntityManager().find(Book.class, id);
		Hibernate.initialize(book.getAuthors());
		return book;
	}
}
