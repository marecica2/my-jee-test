package org.bmsource.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bmsource.dao.GroupDao;
import org.bmsource.model.b.Group;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
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

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public Group save(Group group) {
		group = groupDao.save(group);
		boolean tr = true;
		if (tr)
			throw new RuntimeException();
		return group;
	}

	public void delete(Group group) {
		groupDao.delete(group);
	}
}
