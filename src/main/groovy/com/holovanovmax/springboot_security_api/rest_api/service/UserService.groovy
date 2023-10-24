package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.User

import java.security.Principal

interface UserService {

    List<User> getAllUsers()

    void saveUser(User user)

    User getUser(String id)

    void deleteUser(String id)

    void registerNewUser(User user)

    User findByName(String name)

    User findByPrincipal(Principal principal)

    void updateUserBalance(String id, double balance)


}
    //ToDo
    //У юзера есть баланс, описать бизнес логику, чтобы админ мог добавить/списать деньги на баланс/с баланса, пополнение
    //сделать через тесты, сделать тест контроллера(mvc), сделать моктест на спринг секьюрити


