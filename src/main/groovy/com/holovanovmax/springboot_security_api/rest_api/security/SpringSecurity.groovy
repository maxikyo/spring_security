package com.holovanovmax.springboot_security_api.rest_api.security

import com.holovanovmax.springboot_security_api.rest_api.service.UserNoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SpringSecurity {

    @Autowired private UserDetailsService userDetailsService
    @Autowired private PasswordEncoder passwordEncoder

    @Bean
    static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder()
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(
                                "/api/registration",
                                '/loginPage',
                                '/addMoney',
                                '/tryToAddMoney',
                                'removeMoney',
                                '/registration',
                                '/api/test_2',
                                '/addMoney',
                                '/api/notes/get/all'
                        ).permitAll()
//                        .requestMatchers(         //и так все запросы после авторизации
//                                '/api/notes/get/all/my'
//                        ).authenticated()
//                        .requestMatchers(
//                                'api/notes/delete/{id}'
//                        ).authenticated()
                        .anyRequest().authenticated() //-<любой запрос, но для авторизованных пользователей в дальнейшемю
                ).formLogin(
                form -> form
                        .loginPage("/loginPage")
                        .loginProcessingUrl("/login")
                        .failureUrl("/loginPage?error=true")
                        .defaultSuccessUrl("/")
        ).logout(
                logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        )
        return http.build()

    }


    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)

    }

}

