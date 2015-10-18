package org.bmsource.dao;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.bmsource.model.a.BaseEntity;

public abstract class GenericDao<T extends BaseEntity<T>, ID extends Serializable> {

	private Class<T> entityClass;

	abstract EntityManager em();

	@SuppressWarnings("unchecked")
	public GenericDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	public T find(ID id) {
		return em().find(entityClass, id);
	}

	public T save(final T t) {
		if (t.getId() != null)
			return em().merge(t);
		else {
			em().persist(t);
			return t;
		}
	}

	public TypedQuery<T> filter(T filter) {
		CriteriaBuilder builder = em().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(entityClass);
		Root<T> entity = query.from(entityClass);
		Predicate p = builder.conjunction();
		try {
			for (PropertyDescriptor pd : Introspector.getBeanInfo(entityClass).getPropertyDescriptors()) {
				if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
					if (pd.getReadMethod().invoke(filter) != null) {
						p = builder.and(p, builder.equal(entity.get(pd.getName()), pd.getReadMethod().invoke(filter)));
					}
				}
			}
		} catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		query.select(entity).where(p);
		return em().createQuery(query);
	}

	public List<T> filterList(T filter) {
		TypedQuery<T> q = filter(filter);
		q.setMaxResults(1);
		List<T> results = q.getResultList();
		return results;
	}

	public T filterSingle(T filter) {
		TypedQuery<T> q = filter(filter);
		q.setMaxResults(1);
		List<T> results = q.getResultList();
		if (results.size() > 0)
			return results.get(0);
		return null;
	}

	public void delete(T t) {
		t = em().merge(t);
		em().remove(t);
	}

	public List<T> findAll() {
		TypedQuery<T> q = em().createQuery("from " + entityClass.getName(), entityClass);
		return q.getResultList();
	}

}
