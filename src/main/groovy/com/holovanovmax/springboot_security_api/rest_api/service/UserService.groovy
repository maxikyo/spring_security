package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.User

interface UserService {

    List<User> getAllUsers()

    void saveUsers(User user)

    User getUsers(String id)

    void deleteUser(String id)

    List<User> findAllByName(String name)

}