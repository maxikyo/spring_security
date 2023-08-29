package com.holovanovmax.springboot_security_api.rest_api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true)
class RestApiApplication {

	static void main(String[] args) {
		SpringApplication.run(RestApiApplication, args)
	}

}
