package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.model.UserDto
import com.holovanovmax.springboot_security_api.rest_api.service.BalanceService
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

    @Autowired
    private BalanceService balanceService


    @GetMapping("/api/test_1")
    String test2(Principal principal){
        "ok!"
    }

    @GetMapping("/api/test_2")
    User userTest2(){
//        User user1 = userService.getUser("64dbc0d899b0d0031c739f81")
//        User user2 = userService.getUser("64dbc0d899b0d0031c739f81")
//        log.info(user1.balance.toString(), user2.balance.toString())
//
//        user1.balance = user1.balance + 10
//        user2.balance = user2.balance + 20
//        userService.saveUser(user1)
//        userService.saveUser(user2)
//
//        user1 = userService.getUser("64dbc0d899b0d0031c739f81")
//        user2 = userService.getUser("64dbc0d899b0d0031c739f81")
//        log.info(user1.balance.toString(), user2.balance.toString())

        return user1
    }


    @GetMapping("/loginPage")
    String loginPage(@RequestParam(required = false) boolean error, Model model) {
        User user1 = userService.getUser("64dbc0d899b0d0031c739f81")
        User user2 = userService.getUser("64dbc0d899b0d0031c739f81")
        log.info("User 1 before = ${user1.balance.toString()}") //2 переменные
        log.info("User 2 before = ${user2.balance.toString()}")
        user1.balance = user1.balance + 10
        userService.saveUser(user1)
        user2.balance = user2.balance - 10
        userService.saveUser(user2)

        user1 = userService.getUser("64dbc0d899b0d0031c739f81")
        user2 = userService.getUser("64dbc0d899b0d0031c739f81")
        log.info("User 1 after = ${user1.balance.toString()}")
        log.info("User 2 after = ${user2.balance.toString()}")
        if (error) {
            model.addAttribute("message", "error login")
        }
        return "login"
    }

    @GetMapping("/")
    String mainPage(Model model, Principal principal) {
        User user = userService.findByPrincipal(principal)
        model.addAttribute("name", user.name)
        model.addAttribute("role", user.role)
        return "mainPage"
    }


    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/api/users")
    List<UserDto> showAllUsers() {
        userService.getAllUsers().collect() {
            new UserDto(
                    id: it.id,
                    name: it.name,
                    password: it.password,
                    role: it.role,
                    balance: it.balance
            )
        }
    }


    @ResponseBody
    @PreAuthorize('hasAuthority("ADMIN")')
    @PostMapping("/api/users")
    UserDto addUsers(@RequestBody User user) {
        userService.saveUser(user)
        if (user) {
            return new UserDto(
                    id: user.id,
                    name: user.name,
                    password: user.password,
                    role: user.role,
                    balance: user.balance

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
    @PreAuthorize('hasAuthority("ADMIN")')
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
