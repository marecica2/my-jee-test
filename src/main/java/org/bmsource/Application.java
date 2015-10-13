package org.bmsource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Application {

	static @Bean public PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
		Resource[] resourceLocations = new Resource[] { new ClassPathResource("my.custom.properties"),
				new ClassPathResource("my.other.properties"), };
		p.setLocations(resourceLocations);
		return p;
	}
}
