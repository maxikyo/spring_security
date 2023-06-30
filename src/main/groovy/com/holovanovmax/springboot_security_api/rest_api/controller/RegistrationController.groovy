package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RegistrationController {

    @Autowired
    private UserService userService

    @GetMapping("/registration")
    String showRegistrationForm(Model model) {
        model.addAttribute("user", new User())
        return "registration"
    }

    @PostMapping("/registration")
    String registerNewUser(User user) {
        userService.registerNewUser(user)
        return "redirect:/login"
    }



}
