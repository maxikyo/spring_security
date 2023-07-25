package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Slf4j
@Controller
class UserController {
    @Autowired
    private UserService userService

    @GetMapping("/users")
    List<User> showAllUsers() {
        List<User> allUsers = userService.getAllUsers()
        return allUsers
    }

    @PostMapping("/api/users")
    void addUsers(@RequestBody User user) {
        userService.saveUser(user)
    }

    @GetMapping("/api/users/{id}")
    User getUsers(@PathVariable String id) {
        userService.getUser(id)
    }

    @DeleteMapping("/api/users/{id}")
    ResponseEntity deleteProductById(@PathVariable String id) {
        Optional<User> product = this.userService.getUser(id)

        if (product.isPresent()) {
            this.userService.deleteUser(id)
            return ResponseEntity.ok("Done")
        } else {
            log.warn 'user with id ' + id + ' not found! And did not deleted'
            return new ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/api/search/{name}")
    User findUser(@PathVariable String name){
        User concrete = userService.findByName(name)
        return concrete
    }

    @GetMapping("/api/registration")
    String showRegistrationForm(Model model) {
        model.addAttribute("user", new User())
        return "registration"
    }

    @PostMapping("/api/registration")
    String registerNewUser(User user) {
        userService.registerNewUser(user)
        return "redirect:/login"
    }


}
