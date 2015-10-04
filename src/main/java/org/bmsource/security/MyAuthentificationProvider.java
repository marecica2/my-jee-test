package org.bmsource.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bmsource.controller.UserSession;
import org.bmsource.dao.UserDao;
import org.bmsource.model.a.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@Singleton
public class MyAuthentificationProvider implements AuthenticationProvider {

	static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	static {
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Inject
	UserDao userDao;

	@Inject
	UserSession session;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		User usr = userDao.getByLogin(auth.getName());
		if (usr == null) {
			throw new BadCredentialsException("Bad Credentials");
		} else if (!usr.getPassword().equals(auth.getCredentials())) {
		} else {
			session.setUser(usr);
			return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), AUTHORITIES);
		}
		throw new BadCredentialsException("Bad Credentials");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
