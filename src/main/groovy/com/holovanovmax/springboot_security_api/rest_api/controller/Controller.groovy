package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class Controller {
    @Autowired
    private UserService userService

    @GetMapping("/users")
    List<User> showAllUsers() {
        List<User> allUsers = userService.getAllUsers()
        return allUsers
    }

    @PostMapping("/users")
    void addUsers(@RequestBody List<User> user){
        userService.saveUser(user)
    }

    @GetMapping("/users/{id}")
    User getUsers(@PathVariable String id) {
        User user = userService.getUser(id)
        return user
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity deleteProductById(@PathVariable String id) {
        Optional<User> product = this.userService.deleteUser(id)

        if (product.isPresent()) {
            this.userService.deleteUser(id)
            return ResponseEntity.ok("Done")
        } else {
            log.warn('user with id ' + id + ' not found! And did not deleted')
            return new ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/users")
    List<User> findAllUsersByName(@PathVariable String name) {
        List<User> users = userService.findAllByName(name)
        return users
    }


}
