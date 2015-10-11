package org.bmsource.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.bmsource.dao.UserDao;
import org.bmsource.model.a.Author;
import org.bmsource.model.a.User;
import org.bmsource.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

/*** Root resource(exposed at"myresource"path) */

@Named
@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/test")
public class MyResource {

	@Context
	SecurityContext securityContext;

	@Autowired
	UserDao userDao;

	@Autowired
	AuthorService authorService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getItText() {
		return "Got it in text";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getItJson() {
		Map<String, Object> resp = new HashMap<>();
		resp.put("key", "value");
		resp.put("title", "value");
		return resp;
	}

	@Path("/users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(@QueryParam("param") String param) {
		System.err.println(securityContext);
		System.err.println(securityContext.isSecure());
		List<User> users = userDao.findAll();

		Author author = new Author();
		author.setFirstName("xxx");
		author.setLastName("xxx");
		authorService.save(author);

		return Response.status(200).entity(users).build();
	}
}