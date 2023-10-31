package com.holovanovmax.springboot_security_api.rest_api.service


import com.holovanovmax.springboot_security_api.rest_api.contextLoader.ContextLoader
import com.holovanovmax.springboot_security_api.rest_api.model.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.User


class UserServiceTest extends ContextLoader {


    def "add money to userBalance"() {
        given:
        User user = userService.registerNewUser(new User(
                name: "5454545",
                password: "pass1",
                role: "USER"
        ))

        when:
        User updatedUser = userService.updateUserBalance(user.id, BalanceOperation.PLUS, new BigDecimal("10"))

        then:
        updatedUser.balance == new BigDecimal("11" )
    }
}


//@SpringBootTest
//class UserServiceTest extends Specification {
//
//
//    @Autowired
//    private UserServiceImpl userService
//
//
//    def "add money to userBalance"() {
//        given:
//        User user = userService.registerNewUser(new User(
//                name: "name1",
//                password: "pass1",
//                role: "USER"
//        ))
//        when:
//        user = userService.updateUserBalance(user.id, BalanceOperation.PLUS, new BigDecimal("10"))
//
//        then:
//        user.balance == new BigDecimal("10")
//    }
//}




