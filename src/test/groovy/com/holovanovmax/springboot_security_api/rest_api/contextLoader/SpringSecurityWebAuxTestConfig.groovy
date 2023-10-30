package com.holovanovmax.springboot_security_api.rest_api.contextLoader

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@Configuration
@EnableWebSecurity
class SpringSecurityWebAuxTestConfig {
    @Bean(name = "mvcHandlerMappingIntrospector")
    HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector()
    }
}
