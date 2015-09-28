package org.bmsource.rest;

import org.bmsource.dao.BookDao;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class MyApplication extends ResourceConfig {

	public MyApplication() {
		packages("org.bmsource.rest");
		register(BookDao.class);
		register(MyResource.class);
		register(JacksonJsonProvider.class);
	}
}
