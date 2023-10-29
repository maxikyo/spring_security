package com.holovanovmax.springboot_security_api.rest_api

import com.holovanovmax.springboot_security_api.rest_api.controller.RegistrationController
import com.holovanovmax.springboot_security_api.rest_api.controller.UserController
import com.holovanovmax.springboot_security_api.rest_api.model.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification


@Slf4j
@SpringBootTest
class RestApiApplicationTests{

	@Autowired
	private RegistrationController registrationController

	@Autowired
	private UserController userController

	@Autowired
	private UserService userService

	@Autowired
	private MongoTemplate mongoTemplate

	@Test
	void contextLoads(){
		assert registrationController != null
		assert userController != null
		assert userService != null
		assert mongoTemplate != null

//		if(registrationController){
//			println "registrationController is not Null and working"
//		}
//		if(userController){
//			println "userController is not Null and working"
//		}
//		if(userService){
//			println "userService is not Null and working"
//		}
//		if (mongoTemplate)
//			println "mongoTemplate is not Null and working"
	}
	def "add money to userBalance"() {
		given:
		User user = userService.registerNewUser(new User(
				name: "name2",
				password: "pass1",
				role: "USER"
		))

		when:
		User updatedUser = userService.updateUserBalance(user.id, BalanceOperation.PLUS, new BigDecimal("10"))

		then:
		updatedUser.balance == new BigDecimal("10")
	}
}



