package fr.april.gps.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@Configuration
@ComponentScan(basePackages = {"fr.april.gps.webapi"})
@Profile("test")
public class ApplicationTest {
	public static void main(String... args) {
		new SpringApplication(ApplicationTest.class).run(args);
	}

}
