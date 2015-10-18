package org.bmsource.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bmsource.dao.AuthorDao;
import org.bmsource.dao.GroupDao;
import org.bmsource.model.a.Author;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Singleton
@Component
@Transactional
public class AuthorService {

	@Inject
	AuthorDao authorDao;

	@Inject
	GroupDao groupDao;

	public List<Author> getAuthors() {
		List<Author> authors = authorDao.findAll();
		return authors;
	}

	public List<Author> getFiltered(Author filter) {
		List<Author> authors = authorDao.filterList(filter);
		return authors;
	}

	public Author get(Long id) {
		return authorDao.findEager(id);
	}

	public Author save(Author author) {
		Author create = authorDao.save(author);
		return create;
	}

	public void delete(Author author) {
		authorDao.delete(author);
	}
}
