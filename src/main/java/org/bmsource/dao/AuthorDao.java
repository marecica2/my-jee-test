package org.bmsource.dao;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.bmsource.model.a.Author;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

@Singleton
@Component
public class AuthorDao extends GenericDao<Author, Long> {

	@PersistenceContext(unitName = "PersistenceUnitA")
	protected EntityManager entityManager;

	@Override
	protected EntityManager em() {
		return entityManager;
	}

	public Author findEager(Long id) {
		Author author = em().find(Author.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		Hibernate.initialize(author.getBooks());
		return author;
	}

}
