package com.wipro.cash.transaction.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Bhaskar
 *
 */
@SpringBootApplication
public class CTMSpringBootApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CTMSpringBootApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CTMSpringBootApplication.class, args);
	}
}