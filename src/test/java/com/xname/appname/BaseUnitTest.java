package com.xname.appname;

import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.response.DefaultResponseCreator;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseUnitTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfiguration.class)
@ActiveProfiles("test")
public class BaseUnitTest {

	/** The loader. */
	@Autowired
	private ResourceLoader loader;
	
	/**
	 * With json.
	 *
	 * @param jsonFile the json file
	 * @return the default response creator
	 */
	public DefaultResponseCreator withJson(String jsonFile) {
		return withSuccess(loader.getResource(jsonFile), MediaType.APPLICATION_JSON);
	}
	
}
