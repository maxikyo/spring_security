package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.contextLoader.ContextLoader
import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.model.userNote.NoteCreateDto
import com.holovanovmax.springboot_security_api.rest_api.service.UserNoteService
import org.json.JSON
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean

import java.security.Principal

class UserNoteControllerTest extends ContextLoader {

    @Autowired
    UserNoteController userNoteController

    @MockBean UserNoteService userNoteService

    def "test Post method for api note create"() {
            given:
            User user = userService.registerNewUser(new User(  // - возвращал null
                    name: "5454545",
                    password: "pass1",
                    role: "USER"
            ))
            def noteCreateDto = new NoteCreateDto(content: "Test Content", isPublic: true)
            def principal = new Principal() {
                @Override
                String getName() {
                    return userService.findByName("user")
                }
            } // replace MyPrincipal with your custom Principal class

            when:
            def result = mockMvc.perform(
                    post("/api/notes/create")
                            .contentType("application/json")
                            .content(noteCreateDto as JSON)
                            .principal(principal)
            )

            then:
            result.andExpect(status().isOk())
            // Add more assertions as needed

    }
}