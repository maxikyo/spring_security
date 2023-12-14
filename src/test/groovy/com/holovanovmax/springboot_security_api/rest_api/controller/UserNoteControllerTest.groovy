package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.contextLoader.ContextLoader
import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserNote
import com.holovanovmax.springboot_security_api.rest_api.model.userNote.NoteCreateDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PrePostAdviceReactiveMethodInterceptor
import org.springframework.security.test.context.support.WithMockUser

import java.security.Principal


class UserNoteControllerTest extends ContextLoader {

    @Autowired
    UserNoteController userNoteController

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    def "Should create a new note for an authorized user"() {

        // Use given-when-then blocks to structure the test
        given: "A valid NoteCreateDto"
        def noteCreateDto = new NoteCreateDto(content: "Hello world", isPublic: true)

        when: "The controller method is called with the NoteCreateDto and the principal"
        def principal = new Principal(name: "admin")
        def userNote = webController.getNoteByUser(noteCreateDto, principal)

        then: "The userNote is not null and has the expected values"
        userNote != null
        userNote.content == noteCreateDto.content
        userNote.userId == 1 // Assuming the admin user has id 1
        userNote.isPublic == noteCreateDto.isPublic
    }


    def "Should create a new note for an authorized user"() {

        given: "A valid NoteCreateDto"
        User user = userService.registerNewUser(new User(
             name: "admin",
               password: "pass1",
                role: "ADMIN"
        ))
        def noteCreateDto = new NoteCreateDto(content: "Hello world", isPublic: true)

        when: "The controller method is called with the NoteCreateDto and the principal"
        userService.findByPrincipal(principal)

        def userNote = userNoteController.getNoteByUser(noteCreateDto, principal)

        then: "The userNote is not null and has the expected values"
        userNote != null
        userNote.content == noteCreateDto.content
        userNote.userId == 1 // Assuming the admin user has id 1
        userNote.isPublic == noteCreateDto.isPublic
    }
}






//    def "Should create a new note for an authorized user"() {
//
//        given: "A valid NoteCreateDto"
//        User user = userService.registerNewUser(new User(  // - возвращал null
//                name: "user",
//                password: "pass1",
//                role: "USER"
//        ))
//        NoteCreateDto noteCreateDto = new NoteCreateDto(content: "Hello world", isPublic: true)
//
//        when: "The controller method is called with the NoteCreateDto and the principal"
//        UserNote userNote = userNoteController.getNoteByUser(noteCreateDto, principal)
//
//        then: "The userNote is not null and has the expected values"
//        userNote != null
//        userNote.content == noteCreateDto.content
//        userNote.userId == 1
//        userNote.isPublic == noteCreateDto.isPublic
//    }
//}

