package org.bmsource.model.a;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Author extends BaseEntity<Author> implements Serializable {

	private static final long serialVersionUID = 1432697298389184917L;

	@Version
	Long version;

	@NotEmpty
	@XmlElement
	private String firstName;

	@NotEmpty
	@XmlElement
	private String lastName;

	@ManyToMany(targetEntity = Book.class, mappedBy = "authors")
	Set<Book> books;

	public Author() {
		super();
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Author [firstName=" + firstName + ", lastName=" + lastName + ", getId()=" + getId() + "]";
	}
}
