package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.repository.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

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
    void registerNewUser(User user) {
        if (usersRepository.findByName(user.getName()) != null) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        usersRepository.save(user);
    }

    @Override
    User findByName(String name) {
        usersRepository.findByName(name)
    }


}