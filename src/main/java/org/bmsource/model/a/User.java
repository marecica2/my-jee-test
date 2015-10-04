package org.bmsource.model.a;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "\"user\"")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5286799114334868506L;

	@NotEmpty
	@XmlElement
	@Column(nullable = false)
	private String firstName;

	@NotEmpty
	@XmlElement
	@Column(nullable = false)
	private String lastName;

	@NotEmpty
	@XmlElement
	@Column(nullable = false, unique = true)
	private String login;

	@NotEmpty
	@XmlElement
	@Column(nullable = false)
	private String password;

	public User() {
		super();
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
