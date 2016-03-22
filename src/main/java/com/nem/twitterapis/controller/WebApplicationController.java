package com.xname.appname.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// TODO: Auto-generated Javadoc
/**
 * The Class WebApplicationController.
 */
@Controller
public final class WebApplicationController {

	/**
	 * Test.
	 *
	 * @return the model and view
	 */
	@RequestMapping("/api/test")
	public ModelAndView test() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("time", new Date());
		model.put("message", "test ..!!!");
		return new ModelAndView("index", model);
	}
}
