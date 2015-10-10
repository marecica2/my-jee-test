package org.bmsource.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bmsource.dao.GroupDao;
import org.bmsource.model.b.Group;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Singleton
@Component
@Transactional
public class GroupService {

	@Inject
	GroupDao groupDao;

	public List<Group> getGroups() {
		List<Group> groups = groupDao.findAll();
		return groups;
	}

	public Group get(Long id) {
		return groupDao.find(id);
	}

	public Group save(Group group) {
		return groupDao.create(group);
	}

	public Group update(Group group) {
		return groupDao.update(group);
	}

	public void delete(Group group) {
		groupDao.delete(group);
	}
}
