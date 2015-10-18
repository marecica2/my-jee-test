package org.bmsource.dao;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
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
	protected EntityManager em() {
		return entityManager;
	}

	public Book findLock(Long id) {
		Book book = em().find(Book.class, id, LockModeType.OPTIMISTIC);
		Hibernate.initialize(book.getAuthors());
		return book;
	}
}
