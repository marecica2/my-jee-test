// package org.bmsource.dao;
//
// import java.io.Serializable;
// import java.util.List;
//
// import javax.inject.Singleton;
// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;
// import javax.transaction.Transactional;
//
// import org.springframework.stereotype.Component;
//
// @Transactional
// @Singleton
// @Component
// public class GenericDao<T, ID extends Serializable> {
//
// protected Class<T> persistentClass;
//
// @PersistenceContext
// private EntityManager entityManager;
//
// protected <T> List<T> _all(Class<T> type) {
// return em.createQuery("select _it_ from " + getMetadataUtil().get(type).getEntityName() + " _it_").getResultList();
// }
// public T get(ID id) {
// T t = entityManager.find(persistentClass, id);
// return t;
// }
//
// }
