package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.model.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.service.BalanceService
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView


@Slf4j
@Controller
class RegistrationController {

    @Autowired
    private UserService userService

    @Autowired
    private BalanceService balanceService

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

    @ResponseBody
    @GetMapping("/addMoney")
    User addMoney() {
        User user = userService.findByName("user")
        balanceService.updateUserBalance(user.id, BalanceOperation.PLUS, new BigDecimal("10"))
    }

//    @ResponseBody
//    @GetMapping("/addMoney")
//    User tryToAddMoney() {
//        User user = userService.findByName("user")
//        balanceService.updateUserBalance(user.id, BalanceOperation.PLUS, new BigDecimal("0"))
//    }
//
//    @ResponseBody
//    @GetMapping("/removeMoney")
//    User removeMoney() {
//        User user = userService.findByName("user")
//        balanceService.updateUserBalance(user.id, BalanceOperation.MINUS, new BigDecimal("10"))
//    }
}
