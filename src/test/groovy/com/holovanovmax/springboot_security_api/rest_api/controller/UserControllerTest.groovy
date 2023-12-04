package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.contextLoader.ContextLoader
import org.springframework.beans.factory.annotation.Autowired

class UserControllerTest extends ContextLoader{

        @Autowired
        UserController userController

        def "when context is loaded then all expected beans are created"() {
            expect: "the userController is created"
            userController
        }


    }

