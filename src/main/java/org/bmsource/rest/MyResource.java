package org.bmsource.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/test")
public class MyResource {

	@Context
	SecurityContext securityContext;

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
		System.err.println(param);
		System.err.println(securityContext);
		System.err.println(securityContext.isSecure());

		List<User> users = new ArrayList<>();
		users.add(new User("Marek Balla", "marek.balla@gmail.com"));
		users.add(new User("Dominika Sirotova", "dominikasir@gmail.com"));
		return Response.status(200).entity(users).build();
	}
}