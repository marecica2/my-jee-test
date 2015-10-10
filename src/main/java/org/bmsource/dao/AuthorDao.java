package org.bmsource.dao;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.bmsource.model.a.Author;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

@Singleton
@Component
public class AuthorDao extends GenericDao<Author, Long> {

	@PersistenceContext(unitName = "PersistenceUnitA")
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public Author findEager(Long id) {
		Author author = getEntityManager().find(Author.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		Hibernate.initialize(author.getBooks());
		return author;
	}

	public List<Author> getFiltered(Author filter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Author> query = builder.createQuery(Author.class);
		Root<Author> a = query.from(Author.class);

		Predicate p = builder.conjunction();
		if (filter.getFirstName() != null)
			p = builder.and(p, builder.equal(a.get("firstName"), filter.getFirstName()));
		if (filter.getLastName() != null)
			p = builder.and(p, builder.equal(a.get("lastName"), filter.getLastName()));
		query.select(a).where(p);
		return entityManager.createQuery(query).getResultList();
	}
}
