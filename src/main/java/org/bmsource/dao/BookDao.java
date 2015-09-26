package org.bmsource.dao;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bmsource.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Singleton
@Component
public class BookDao extends GenericDao<Book, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	@PostConstruct
	public void init() {
		System.out.println("bookdao init");
	}

}
