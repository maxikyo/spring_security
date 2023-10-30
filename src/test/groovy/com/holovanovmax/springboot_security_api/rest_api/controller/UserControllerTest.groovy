//package com.holovanovmax.springboot_security_api.rest_api.controller
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import com.holovanovmax.springboot_security_api.rest_api.service.UserService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.test.web.servlet.MockMvc
//import spock.lang.Specification


//@WebMvcTest(UserController.class)
//class UserControllerTest extends Specification{
//
//    @MockBean
//    UserService userService
//
//    ObjectMapper mapper = new ObjectMapper()
//
//    @Autowired
//    private MockMvc mvc
//
//    def "when get is performed then the response has status 200 and content is LoginPage"() {
//        expect: "Status is 200 and the response is 'loginPage'"
//        mvc.perform(get("/loginPage"))
//                .andExpect(status().isOk())
//                .andReturn()
//                .response
//    }
//}
