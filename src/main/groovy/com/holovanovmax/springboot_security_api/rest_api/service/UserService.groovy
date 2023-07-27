package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.User

import java.security.Principal

interface UserService {

    List<User> getAllUsers()

    void saveUser(User user)

    User getUser(String id)

    void deleteUser(String id)

    void registerNewUser (User user)

    User findByName(String name)

    User findByPrincipal(Principal principal)

}