package com.connellboyce.webportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Connell Boyce on Jan 13 2021
 */
@SpringBootApplication
@RestController
public class WebAppResume {

	public static void main(String[] args) {
		SpringApplication.run(WebAppResume.class, args);
	}

}
