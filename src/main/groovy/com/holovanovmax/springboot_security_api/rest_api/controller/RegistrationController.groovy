package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.model.userBalance.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserBalance
import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.model.userInformation.UserDto
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
    ModelAndView registerNewUser(UserDto userDto, ModelMap model) {
        try {
            userService.registerNewUser(new User(
                    name: userDto.name,
                    password: userDto.password,
                    role: userDto.role
            ))
            model.addAttribute("message","registration successful")
        }catch(RuntimeException e){
            model.addAttribute("message",e.message)
        }
        return new ModelAndView("login", model)
    }


    @ResponseBody
    @GetMapping("/addMoney")
    UserDto tryToAddMoney() {
        User user = userService.findByName("user1")
        UserBalance userBalance = balanceService.updateUserBalance(user.id, BalanceOperation.PLUS
                , new BigDecimal("10"))
        return new UserDto(
                id: user.id,
                name: user.name,
                password: user.password,
                role: user.role,
                balance: userBalance.balance
        )
    }

    @ResponseBody
    @GetMapping("/removeMoney")
    UserDto removeMoney() {
        User user = userService.findByName("user")
        UserBalance userBalance = balanceService.updateUserBalance(user.id, BalanceOperation.MINUS, new BigDecimal("10"))
        return new UserDto(
                id: user.id,
                name: user.name,
                password: user.password,
                role: user.role,
                balance: userBalance.balance
        )
    }
}
