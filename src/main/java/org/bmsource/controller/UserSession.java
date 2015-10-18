package org.bmsource.controller;

import java.io.Serializable;

import org.bmsource.model.b.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession implements Serializable {
	private static final long serialVersionUID = -4646428648200049402L;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User loggedUser) {
		this.user = loggedUser;
	}

}
