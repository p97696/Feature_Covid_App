package com.apple.wipro.corona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
//@EnableZuulProxy
@ComponentScan(basePackages={"com.apple.wipro.corona"})
public class CoronaApplication {
	public static void main(String args[]) {
		SpringApplication.run(CoronaApplication.class, args);
	}
}
