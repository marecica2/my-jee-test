package org.bmsource.model;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
public class Book extends BaseEntity {

	@NotEmpty
	@XmlElement
	private String title;

	@NotEmpty
	@XmlElement
	private String author;

	@Min(value = 1, message = "{javax.validation.constraints.Min.message}")
	@XmlElement
	private int pages;

	public Book() {
		super();
	}

	public Book(String title, String author, int pages) {
		super();
		this.title = title;
		this.author = author;
		this.pages = pages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", pages=" + pages + ", getId()=" + getId() + "]";
	}
}
