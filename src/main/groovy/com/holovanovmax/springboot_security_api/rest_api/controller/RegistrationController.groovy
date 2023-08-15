package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView


@Slf4j
@Controller
class RegistrationController {

    @Autowired

    private UserService userService

    @GetMapping("/registration")
    String registrationPage(Model model) {
        return "registration"
    }

    @PostMapping("/api/registration")
    ModelAndView registerNewUser(User user, ModelMap model) {
        try {
            userService.registerNewUser(user)
            model.addAttribute("message","registration successful")
        }catch(RuntimeException e){
            model.addAttribute("message",e.message)
        }
        return new ModelAndView("login", model)
    }
}
