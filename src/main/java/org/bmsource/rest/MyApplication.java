package org.bmsource.rest;

import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class MyApplication extends ResourceConfig {

	public MyApplication() {
		packages("org.bmsource.rest");
		register(MyResource.class);
		register(JacksonJsonProvider.class);
	}
}
