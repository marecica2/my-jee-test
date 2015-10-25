package org.bmsource.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bmsource.dao.UserDao;
import org.bmsource.model.b.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Singleton
@Service
@Transactional
public class UserService {

	@Inject
	UserDao userDao;

	public List<User> getUsers() {
		List<User> users = userDao.findAll();
		return users;
	}

	public User get(Long id) {
		return userDao.find(id);
	}

	public User getByLogin(String login) {
		User filter = new User();
		filter.setLogin(login);
		User u = userDao.filterSingle(filter);
		return u;
	}

	public User save(User user) {
		return userDao.save(user);
	}

	public void delete(User user) {
		userDao.delete(user);
	}
}
