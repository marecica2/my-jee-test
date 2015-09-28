package org.bmsource.dao;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.TypedQuery;

import org.bmsource.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Singleton
@Named
@Component
public class UserDao extends GenericDao<User, Long> {

	public User getByLogin(String login) {
		TypedQuery<User> q = entityManager.createQuery("from User where login = :login", User.class);
		q.setParameter("login", login);
		List<User> users = q.getResultList();
		if (users.size() > 0)
			return users.get(0);
		return null;
	}

}