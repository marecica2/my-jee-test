package org.bmsource.model.a;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.beans.BeanUtils;

@MappedSuperclass
public class BaseEntity<E> {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	public <T> E initProperties(T source) {
		BeanUtils.copyProperties(source, this, new String[] { "id", "version" });
		return (E) this;
	}
}
