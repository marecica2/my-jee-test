package org.bmsource.dao;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bmsource.model.a.Book;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Singleton
@Named
public class BookDao extends GenericDao<Book, Long> {

	@PersistenceContext(unitName = "PersistenceUnitA")
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
