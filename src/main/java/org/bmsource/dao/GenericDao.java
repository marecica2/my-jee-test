package org.bmsource.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Transactional
public abstract class GenericDao<T, ID extends Serializable> {

	private Class<T> entityClass;

	@Inject
	private PlatformTransactionManager transactionManager;

	protected abstract EntityManager getEntityManager();

	@SuppressWarnings("unchecked")
	public GenericDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@PostConstruct
	public void init() {
		System.out.println("GenericDao init");
	}

	public T create(T t) {
		getEntityManager().persist(t);
		return t;
	}

	public T find(ID id) {
		return getEntityManager().find(entityClass, id);
	}

	@Transactional(propagation = Propagation.NEVER)
	public T update(final T t) {
		final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		return transactionTemplate.execute(new TransactionCallback<T>() {
			@Override
			public T doInTransaction(TransactionStatus paramTransactionStatus) {
				T result = getEntityManager().merge(t);
				return result;
			}
		});
	}

	public void delete(T t) {
		t = getEntityManager().merge(t);
		getEntityManager().remove(t);
	}

	public List<T> findAll() {
		TypedQuery<T> q = getEntityManager().createQuery("from " + entityClass.getName(), entityClass);
		return q.getResultList();
	}

}
