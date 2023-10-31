package com.holovanovmax.springboot_security_api.rest_api

import com.holovanovmax.springboot_security_api.rest_api.contextLoader.ContextLoader
import groovy.util.logging.Slf4j
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest


@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [
        ContextLoader,
])
class RestApiApplicationTests {

}



