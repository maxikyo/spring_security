package com.holovanovmax.springboot_security_api.rest_api.service

//import com.holovanovmax.springboot_security_api.rest_api.model.userBalance.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.data.reposiroty.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import java.security.Principal

@Service
class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository usersRepository

    @Autowired
    private PasswordEncoder passwordEncoder

    @Override
    List<User> getAllUsers() {
        return usersRepository.findAll()
    }

    @Override
    User saveUser(User user) {  // - возвращал null
        usersRepository.save(user)
    }

    @Override
    User getUser(String id) {
        usersRepository.findById(id).orElse(null)
    }

    @Override
    void deleteUser(String id) {
        usersRepository.deleteById(id)
    }

    @Override
    User registerNewUser(User user) {  // // - возвращал null
        if(Objects.isNull(user.name)){
            throw new RuntimeException("Неправильное имя")
        }
        if(Objects.isNull(user.password)){
            throw new RuntimeException("Неправильный пароль")
        }

        if (!Objects.isNull(findByName(user.name))) {
            throw new RuntimeException("Пользователь с таким именем уже существует")
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword())
        user.setPassword(encodedPassword)
        saveUser(user)
    }


    @Override
    User findByName(String name) {
        usersRepository.findByName(name).orElse(null)
    }

    @Override
    User findByPrincipal(Principal principal){
        if (!principal){
            return null
        }else{
            return findByName(principal.getName())
        }
    }

    @Override
    Boolean isExist(String userId) {
        usersRepository.existsById(userId)  // метод чтобы не доставать сущность с базы, а просто проверять наличие.
    }                                       //если груви, убирать return
}