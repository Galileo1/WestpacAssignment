package org.galileo1.bdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ="org.galileo1")
public class TestApplication {
	
	public static void main(final String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
