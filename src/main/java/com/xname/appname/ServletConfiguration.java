package com.xname.appname;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * The Class ServletConfiguration.
 */
/*
 * Entry point for the applicaion in a Servlet 3.0 environment
 * 
 * Tomcat 7 or similar containers will load this class, which will eventually
 * load Main and from there everything else is loaded.
 */
public class ServletConfiguration extends SpringBootServletInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.context.web.SpringBootServletInitializer#
	 * configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Main.class);
	}
}