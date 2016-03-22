package com.xname.appname;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * The Class Main.
 */
/*
 * Enable Spring web security. See ApplicationSecurity.java and
 * AuthenticationSecurity.java
 */
@EnableWebSecurity

/*
 * This is a spring configuration bean. Spring will automatically load beans
 * defined in this class
 */
@Configuration

/*
 * Enable spring boot's auto-configuration. This automatically detects libraries
 * in the classpath and sets up various beans
 */
@EnableAutoConfiguration

/*
 * Scan for @Service, @Component, @Bean etc under the current package .. and
 * automatically manage them, dependency inject them etc.
 */
@ComponentScan

@EnableWebMvc

/*
 * Don't load this configuration when unit tests are being executed Instead, use
 * TestConfiguration.java. See BaseUnitTest.java as well
 */
@Profile("!jenkinstest")

/*
 * This is the main entry point of the application. Running this program starts
 * a tomcat internally.
 * 
 * When deployed as a WAR file, ServletConfiguration.java is the entry point.
 * But that class simply delegates to Main as well to start the application.
 */

@EnableSwagger
public class Main extends WebMvcConfigurerAdapter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	// @Autowired
	// private DataSourceProperties dataSourceProperties;

	/** The spring swagger config. */
	private SpringSwaggerConfig springSwaggerConfig;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {
		SpringApplication.run(Main.class, args);
		LOGGER.info("Started Application.");
	}

	/**
	 * Gets the object mapper.
	 *
	 * @return the object mapper
	 */
	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	// FIXME : Unable to remove this, although its not needed.
	/**
	 * Filter registration bean.
	 *
	 * @return the filter registration bean
	 */
	// If removed the interceptor is not getting called.
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new AuthenticationFilter());
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/api/.*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #addInterceptors(org.springframework.web.servlet.config.annotation.
	 * InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/api/**");
	}

	/*
	 * 
	 * Hikari configurations for database connections
	 * 
	 */
	// @Bean
	// public DataSource getDataSource()
	// {
	// HikariConfig hikariConfig = new HikariConfig();
	// hikariConfig.setJdbcUrl(dataSourceProperties.getUrl());
	// hikariConfig.setMaximumPoolSize(50);
	// hikariConfig.setUsername(dataSourceProperties.getUsername());
	// hikariConfig.setPassword(dataSourceProperties.getPassword());
	// hikariConfig.setDriverClassName(dataSourceProperties.getDriverClassName());
	// hikariConfig.setIdleTimeout(3000);
	//
	// HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
	// return hikariDataSource;
	// }

	/**
	 * ******************************************************** SWAGGER
	 * CONFIGURATION START
	 * ********************************************************.
	 *
	 * @param springSwaggerConfig
	 *            the new spring swagger config
	 */

	/**
	 * Sets the spring swagger config.
	 *
	 * @param springSwaggerConfig
	 *            the new spring swagger config
	 */
	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	/**
	 * Custom implementation.
	 *
	 * @return the swagger spring mvc plugin
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns("/api/.*");
	}

	/**
	 * Api info.
	 *
	 * @return the api info
	 */
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("REST APIs", "APIs to test.", "", "", "", "");
		return apiInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #configureDefaultServletHandling(org.springframework.web.servlet.config.
	 * annotation.DefaultServletHandlerConfigurer)
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * Gets the http client.
	 *
	 * @return the http client
	 */
	@Bean
	public HttpClient getHttpClient() {
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(50);
		SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(300).build();
		connManager.setDefaultSocketConfig(socketConfig);
		HttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();
		return httpClient;
	}
}
