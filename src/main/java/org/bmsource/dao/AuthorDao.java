package org.bmsource.dao;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bmsource.model.Author;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Singleton
@Component
public class AuthorDao extends GenericDao<Author, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PostConstruct
	public void init() {
		System.out.println("authordao init");
	}

}
