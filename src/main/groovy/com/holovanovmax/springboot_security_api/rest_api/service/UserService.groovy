package com.holovanovmax.springboot_security_api.rest_api.service

//import com.holovanovmax.springboot_security_api.rest_api.model.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.User

import java.security.Principal

interface UserService {

    List<User> getAllUsers()

    User saveUser(User user) //-> была проблема, что метод возвращал воид, по этому не работало в тестах.

    User getUser(String id)

    void deleteUser(String id)

    User registerNewUser(User user) //->saveUser was return void

    User findByName(String name)

    User findByPrincipal(Principal principal)

}



