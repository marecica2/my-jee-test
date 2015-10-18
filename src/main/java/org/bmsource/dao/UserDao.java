package org.bmsource.dao;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bmsource.model.b.User;
import org.springframework.stereotype.Component;

@Singleton
@Component
public class UserDao extends GenericDao<User, Long> {

	@PersistenceContext(unitName = "PersistenceUnitB")
	protected EntityManager entityManager;

	public User getByLogin(String login) {
		User filter = new User();
		filter.setLogin(login);
		User u = filterSingle(filter);
		return u;
	}

	@Override
	protected EntityManager em() {
		return entityManager;
	}

}