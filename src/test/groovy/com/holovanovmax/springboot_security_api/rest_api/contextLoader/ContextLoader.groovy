package com.holovanovmax.springboot_security_api.rest_api.contextLoader

import com.holovanovmax.springboot_security_api.rest_api.RestApiApplication
import com.holovanovmax.springboot_security_api.rest_api.security.UserDetailsServiceImpl
import com.holovanovmax.springboot_security_api.rest_api.service.BalanceService
import com.holovanovmax.springboot_security_api.rest_api.service.UserNoteService
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification
import org.springframework.security.core.context.SecurityContextHolder

import java.security.Principal

@Slf4j
@ContextConfiguration(classes = [RestApiApplication])
@WebAppConfiguration
@TestPropertySource(properties = ["de.flapdoodle.mongodb.embedded.version=5.0.5"])
@Component
class ContextLoader extends Specification implements ApplicationContextInitializer<ConfigurableApplicationContext> {


    @Autowired public UserService userService

    @Autowired public BalanceService balanceService

    @Autowired public UserNoteService userNoteService

    @Autowired public UserDetailsServiceImpl userDetailsServiceImpl



    void setup(){
        log.info("setup")
    }

    @Override
    void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("init")
    }

    Authentication getCurrentAuthentication(){
        SecurityContextHolder.getContext().getAuthentication()
    }
}
