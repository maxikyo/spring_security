package com.holovanovmax.springboot_security_api.rest_api.security

import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MyUserDetails implements UserDetails{

    private User user

    MyUserDetails(User user){
        this.user = user
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities
    }

    @Override
    String getPassword() {
        return password
    }

    @Override
    String getUsername() {
        return username
    }

    @Override
    boolean isAccountNonExpired() {
        return accountNonExpired
    }

    @Override
    boolean isAccountNonLocked() {
        return accountNonLocked
    }

    @Override
    boolean isCredentialsNonExpired() {
        return credentialsNonExpired
    }

    @Override
    boolean isEnabled() {
        return enabled
    }
}
