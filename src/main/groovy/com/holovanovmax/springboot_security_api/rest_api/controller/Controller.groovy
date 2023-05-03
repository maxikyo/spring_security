package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {
    @Autowired
    private UserService userService

    @GetMapping("/users")
    List<User> showAllUsers() {
        List<User> allUsers = userService.getAllUsers()
        return allUsers
    }

    @GetMapping("/users/{id}")
    User getUsers(@PathVariable String id) {
        User user = userService.getUser(id)
        return user
    }

    @PostMapping("/users")
    void addUsers(@RequestBody List<User> users){
        userService.saveUsers(users)
    }

}
