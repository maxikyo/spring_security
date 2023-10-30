package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.repository.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import java.security.Principal

@Service
class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository

    @Autowired

    private PasswordEncoder passwordEncoder

    @Override
    List<User> getAllUsers() {
        return usersRepository.findAll()

    }

    @Override
    void saveUser(User user) {
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
    User registerNewUser(User user) {
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
        usersRepository.save(user)
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
    User updateUserBalance(String id, BalanceOperation balanceOperation, BigDecimal amount) {
        User user = getUser(id)
        if (user){
            if(balanceOperation == BalanceOperation.PLUS){
                user.balance = user.balance + amount
            }
            if(balanceOperation == BalanceOperation.MINUS){
                if(amount > user.balance){
                    throw new IllegalArgumentException("Balance is ${user.balance} less then ${amount}")
                }
                user.balance = user.balance - amount
            }
            saveUser(user)
        }else
            throw new IllegalArgumentException("User with ${id} did not found")
    }

}