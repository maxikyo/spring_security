package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.model.UserDto
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

import java.security.Principal

@Slf4j
@Controller
class UserController {
    @Autowired
    private UserService userService


    @PreAuthorize('hasAuthority("ADMIN") or hasAuthority("USER")')
    @GetMapping("/loginPage")
    String loginPage(@RequestParam(required = false) boolean error, Model model) {
        if (error) {
            model.addAttribute("message", "error login")
        }
        return "login"
    }

    @PreAuthorize('hasAuthority("ADMIN") or hasAuthority("USER")')
    @GetMapping("/")
    String mainPage(Model model, Principal principal) {
        User user = userService.findByPrincipal(principal)
        model.addAttribute("name", user.name)
        model.addAttribute("role", user.role)
        return "mainPage"
    }


    @ResponseBody
    @PreAuthorize('hasAuthority("ADMIN")')
    @GetMapping("/api/users")
    List<UserDto> showAllUsers() {
        userService.getAllUsers().collect() {
            new UserDto(
                    name: it.name,
                    password: it.password,
                    role: it.role,
            )
        }
    }


    @ResponseBody
    @PreAuthorize('hasAuthority("ADMIN") or hasAuthority("USER")')
    @PostMapping("/api/users")
    UserDto addUsers(@RequestBody User user) {
        userService.saveUser(user)
        if (user) {
            return new UserDto(
                    id: user.id,
                    name: user.name,
                    password: user.password,
                    role: user.role
            )
        } else {
            return null
        }
    }

    @ResponseBody
    @PreAuthorize('hasAuthority("ADMIN") or hasAuthority("USER")')
    @GetMapping("/api/users/{id}")
    UserDto getUsers(@PathVariable String id) {
        User user = userService.getUser(id)
        if (user){
            return new UserDto(
                    name: user.name,
                    password: user.password,
                    role: user.role
            )
        }else {
            return null
        }
    }

    @ResponseBody
    @PreAuthorize('hasAuthority("ADMIN")')
// or hasAuthority("USER")
    @DeleteMapping("/api/users/{id}")
    ResponseEntity deleteUserById(@PathVariable String id) {
        User user = userService.getUser(id)
        if (user) {
            userService.deleteUser(id)
            return ResponseEntity.ok("Done")
        } else {
            log.warn 'user with id ' + id + ' not found! And did not deleted'
            return new ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @ResponseBody
    @PreAuthorize('hasAuthority("ADMIN") or hasAuthority("USER")')
    @GetMapping("/api/search/{name}")
    UserDto findUser(@PathVariable String name){
        User user = userService.findByName(name)
        if (user){
            return new UserDto(
                name: user.name,
                password: user.password,
                role: user.role
            )
        }else {
            return null
        }
    }
}
