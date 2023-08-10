package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

import java.security.Principal

@Slf4j
@Controller
class UserController {
    @Autowired
    private UserService userService

    @GetMapping("/loginPage")
    String loginPage(@RequestParam (required = false) boolean error, Model model) {
        if(error){
            model.addAttribute("message", "error login")
        }
        return "login"
    }

    @GetMapping("/registration")
    String registrationPage(Model model) {
        return "registration"
    }

    @GetMapping("/")
    String mainPage(Model model, Principal principal) {
        User user = userService.findByPrincipal(principal)
        model.addAttribute("name",user.name)
        model.addAttribute("role",user.role)
        return "mainPage"
    }


    @ResponseBody
    @GetMapping("/api/users")
    List<User> showAllUsers() {
        List<User> allUsers = userService.getAllUsers()
        return allUsers
    }

    @ResponseBody
    @PostMapping("/api/users")
    void addUsers(@RequestBody User user) {
        userService.saveUser(user)
    }

    @ResponseBody
    @PreAuthorize('hasAuthority("ADMIN") and hasAuthority("Client")')
    @GetMapping("/api/users/{id}")
    User getUsers(@PathVariable String id) {
        userService.getUser(id)
    }

    @ResponseBody
    @DeleteMapping("/api/users/{id}")
    ResponseEntity deleteProductById(@PathVariable String id) {
        Optional<User> user = userService.getUser(id)

        if (user.isPresent()) {
            this.userService.deleteUser(id)
            return ResponseEntity.ok("Done")
        } else {
            log.warn 'user with id ' + id + ' not found! And did not deleted'
            return new ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @ResponseBody
    @GetMapping("/api/search/{name}")
    User findUser(@PathVariable String name){
        User concrete = userService.findByName(name)
        return concrete
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
