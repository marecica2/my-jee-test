package org.bmsource.controller;

import java.io.Serializable;

import org.bmsource.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = WebApplicationContext.SCOPE_SESSION)
public class UserSession implements Serializable {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User loggedUser) {
		this.user = loggedUser;
	}

}
