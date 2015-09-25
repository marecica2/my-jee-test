package org.bmsource.dao;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.bmsource.model.Book;
import org.springframework.stereotype.Component;

@Transactional
@Singleton
@Component
public class BookDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Book find(Long id) {
		return entityManager.find(Book.class, id);
	}

	public Book save(Book book) {
		entityManager.persist(book);
		return book;
	}

	public Book update(Book book) {
		entityManager.merge(book);
		entityManager.flush();
		return book;
	}

	public Book delete(Book book) {
		book = entityManager.merge(book);
		entityManager.remove(book);
		return book;
	}

	public List<Book> findAll() {
		TypedQuery<Book> q = entityManager.createQuery("from Book", Book.class);
		return q.getResultList();
	}
}
