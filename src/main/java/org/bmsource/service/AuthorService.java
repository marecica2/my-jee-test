package org.bmsource.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bmsource.dao.AuthorDao;
import org.bmsource.model.a.Author;
import org.springframework.stereotype.Component;

@Singleton
@Component
public class AuthorService {

	@Inject
	AuthorDao authorDao;

	public List<Author> getAuthors() {
		List<Author> authors = authorDao.findAll();
		return authors;
	}

	public List<Author> getFiltered(Author filter) {
		List<Author> authors = authorDao.getFiltered(filter);
		return authors;
	}

	public Author get(Long id) {
		return authorDao.findEager(id);
	}

	public Author save(Author author) {
		return authorDao.create(author);
	}

	public Author update(Author author) {
		return authorDao.update(author);
	}

	public void delete(Author author) {
		authorDao.delete(author);
	}
}
