package com.holovanovmax.springboot_security_api.rest_api.data.reposiroty

import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository extends MongoRepository <User, String>{
     Optional<User> findByName(String name)
}