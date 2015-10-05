package org.bmsource.dao;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bmsource.model.b.Group;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Singleton
@Named
public class GroupDao extends GenericDao<Group, Long> {

	@PersistenceContext(unitName = "emfB")
	protected EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
