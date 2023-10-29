package com.holovanovmax.springboot_security_api.rest_api.repository

import com.holovanovmax.springboot_security_api.rest_api.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UsersRepository extends MongoRepository <User, String>{
     Optional<User> findByName(String name)

}