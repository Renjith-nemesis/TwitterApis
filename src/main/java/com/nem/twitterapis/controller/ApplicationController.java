package com.xname.appname.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * The Class ApplicationController.
 */
@RestController
@RequestMapping(value = "/api/draft/application")
@Api(value = "application", description = "Api's for the application")
public class ApplicationController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

	/**
	 * Hello.
	 *
	 * @return the string
	 */
	@ApiOperation(value = "Tests Hello World.")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE, value = "/helloWorld")
	public String testHello()

	{
		long start = System.currentTimeMillis();
		String text = "Hello World..!!";
		LOGGER.info("Returning text {}. Time taken:{}.", text, (System.currentTimeMillis() - start));
		return text;
	}
}
