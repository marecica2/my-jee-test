package org.bmsource.dao;

import javax.inject.Singleton;

import org.bmsource.model.Author;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Singleton
@Component
public class AuthorDao extends GenericDao<Author, Long> {

}
