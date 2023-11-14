package com.holovanovmax.springboot_security_api.rest_api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@EnableMongoAuditing()
@SpringBootApplication(scanBasePackages = "com.holovanovmax.springboot_security_api.rest_api")
@EnableMethodSecurity(prePostEnabled = true)
class RestApiApplication {

	static void main(String[] args) {
		SpringApplication.run(RestApiApplication, args)
	}

}
