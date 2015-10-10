package org.bmsource.dao;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bmsource.model.b.Group;
import org.springframework.stereotype.Component;

@Singleton
@Component
public class GroupDao extends GenericDao<Group, Long> {

	@PersistenceContext(unitName = "PersistenceUnitB")
	protected EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
