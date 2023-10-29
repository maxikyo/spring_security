package com.holovanovmax.springboot_security_api.rest_api

import com.holovanovmax.springboot_security_api.rest_api.controller.RegistrationController
import com.holovanovmax.springboot_security_api.rest_api.controller.UserController
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import com.holovanovmax.springboot_security_api.rest_api.service.UserServiceImpl
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate


@Slf4j
@SpringBootTest
class RestApiApplicationTests{

//	@Autowired
//	private RegistrationController registrationController
//
//	@Autowired
//	private UserController userController
//
//	@Autowired
//	private UserServiceImpl userService

	def setup() {
		// Инициализация userService перед каждым тестом
		// Пример: userService = new UserServiceImpl()
	}

	@Autowired
	private MongoTemplate mongoTemplate

	@Test
	void contextLoads(){
		if(registrationController){
			println "registrationController is not Null and working"
		}
		if(userController){
			println "userController is not Null and working"
		}
		if(userService){
			println "userService is not Null and working"
		}
		if (mongoTemplate)
			println "mongoTemplate is not Null and working"
	}
}


