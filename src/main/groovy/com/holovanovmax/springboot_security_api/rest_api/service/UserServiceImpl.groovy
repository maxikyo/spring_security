package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.repository.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository

    @Override
    List<User> getAllUsers(){
        return usersRepository.findAll().collect(){
            new User(name: it.name, email: it.email)
        }
    }

    @Override
    void saveUser(User user) {
        usersRepository.save(user.collect() {
            new User(name: it.name, email: it.email)
        })
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
    List<User> findAllByName(String name) {
        List<User> users = usersRepository.findAllByName(name)
        return users
    }

}
