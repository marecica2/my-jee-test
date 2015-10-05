package org.bmsource.dao;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bmsource.model.a.Author;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Singleton
@Component
public class AuthorDao extends GenericDao<Author, Long> {

	@PersistenceContext(unitName = "PersistenceUnitA")
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
