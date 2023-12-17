package com.holovanovmax.springboot_security_api.rest_api.security

import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.service.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userDetailsService")
 class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserServiceImpl userService

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByName(username)
        if (user == null) {
            throw new UsernameNotFoundException("User not found")
        }
        return new org.springframework.security.core.userdetails.User(
                user.name,
                user.password,
                [new SimpleGrantedAuthority(user.role)]
        )
    }
}
