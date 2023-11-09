package com.holovanovmax.springboot_security_api.rest_api.service


import com.holovanovmax.springboot_security_api.rest_api.contextLoader.ContextLoader
import com.holovanovmax.springboot_security_api.rest_api.model.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.User


class UserServiceTest extends ContextLoader {

    // -> test plus amount for new User.
    def "add money to userBalance"() {
        given:
        User user = userService.registerNewUser(new User(  // - возвращал null
                name: "5454545",
                password: "pass1",
                role: "USER"
        ))

        when:
        User updatedUser = userService.updateUserBalance(user.id, BalanceOperation.PLUS
                , new BigDecimal("10"))

        then:
        updatedUser.balance == new BigDecimal("10" )
    }

    // -> test minus amount for new User with 50$ exist balance (starter bonus for example)
    def "remove money from UserBalance"(){
        given:
        User user1 = userService.registerNewUser(new User(
                name: "useruser",
                password: "pass1",
                role: "USER",
                balance: 50
        ))

        when:
        User updatedUser = userService.updateUserBalance(user1.id, BalanceOperation.MINUS
                , new BigDecimal("10"))

        then:
        updatedUser.balance == new BigDecimal("40")
    }

    // -> test plus 0 to the balance
    def "try to add 0 to userBalance"() {
        given:
        User user = userService.registerNewUser(new User(  // - возвращал null
                name: "55664546",
                password: "pass1",
                role: "USER"
        ))

        when:
        User updatedUser = userService.updateUserBalance(user.id, BalanceOperation.PLUS
                , new BigDecimal("0"))

        then:
        updatedUser.balance == new BigDecimal("10" )
    }

    // -> test minus 0 to the balance
    def "try to remove 0 to userBalance"() {
        given:
        User user = userService.registerNewUser(new User(  // - возвращал null
                name: "user1user2",
                password: "pass1",
                role: "USER",
                balance: 10
        ))

        when:
        User updatedUser = userService.updateUserBalance(user.id, BalanceOperation.MINUS
                , new BigDecimal("0"))

        then:
        updatedUser.balance == new BigDecimal("10" )
    }



    def "if there will PLUS negative on amount"(){
        given:
        User user2 = userService.registerNewUser(new User(
                name: "useruser3",
                password: "pass1",
                role: "USER",
                balance: 50
        ))

        when:
        User updatedUser = userService.updateUserBalance(user2.id, BalanceOperation.PLUS
                , new BigDecimal("-10"))

        then:
        updatedUser.balance == new BigDecimal("50")
    }

    def "if there will MINUS negative on amount"(){
        given:
        User user3 = userService.registerNewUser(new User(
                name: "useruser4",
                password: "pass1",
                role: "USER",
                balance: 50
        ))

        when:
        User updatedUser = userService.updateUserBalance(user3.id, BalanceOperation.MINUS
                , new BigDecimal("-10"))

        then:
        updatedUser.balance == new BigDecimal("50")
    }
    // -> test




//    def "add money to userBalance"() {
//        userService.updateUserBalance(userService.getUser("test").getId()){ если с айди не существует
//        }
//        given:
//        User user = userService.registerNewUser(new User(  // - возвращал null
//                name: "5454545",
//                password: "pass1",
//                role: "USER"
//        ))
//
//        when:
//        User updatedUser = userService.updateUserBalance(user.id, BalanceOperation.PLUS, new BigDecimal("10"))
//
//        then:
//        updatedUser.balance == new BigDecimal("10" )
//    }
}





