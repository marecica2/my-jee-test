package org.bmsource.model.b;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.bmsource.model.a.BaseEntity;

@Entity
@Table(name = "\"group\"")
public class Group extends BaseEntity<Group> {

	private String name;

	public Group() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
