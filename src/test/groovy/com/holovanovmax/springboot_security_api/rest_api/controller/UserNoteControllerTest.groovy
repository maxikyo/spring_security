package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.contextLoader.ContextLoader
import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.model.userNote.NoteCreateDto
import com.holovanovmax.springboot_security_api.rest_api.security.MyUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.context.support.WithUserDetails

import java.security.Principal


class UserNoteControllerTest extends ContextLoader {

    @Autowired
    UserNoteController userNoteController

    @WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl", value = "useruser")
    def "Should create a new note for an authorized user"() {
        given: "A valid NoteCreateDto"
//        def user = userService.registerNewUser(new User(
//                name: "user",
//                password: "123",
//                role: "USER"))
        def noteCreateDto = new NoteCreateDto(content: "Hello world", isPublic: true)

        when: "The controller method is called with the NoteCreateDto and the principal"
        MyUserDetails myUserDetails = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        def userNote = userNoteController.getNoteByUser(noteCreateDto, principal)

        then: "The userNote is not null and has the expected values"
        userNote != null
        userNote.content == noteCreateDto.content
        userNote.userId == user.id
        userNote.isPublic == noteCreateDto.isPublic
    }
}

//@WithMockUser(username = "user", password = "123", authorities = ["USER"])
//def "Should create a new note for an authorized user"() {
//    given: "A valid NoteCreateDto"
//    def user = userService.registerNewUser(new User(
//            name: "user",
//            password: "123",
//            role: "USER"))
//    def noteCreateDto = new NoteCreateDto(content: "Hello world", isPublic: true)
//
//    when: "The controller method is called with the NoteCreateDto and the principal"
//    Principal principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    def userNote = userNoteController.getNoteByUser(noteCreateDto, principal)
//
//    then: "The userNote is not null and has the expected values"
//    userNote != null
//    userNote.content == noteCreateDto.content
//    userNote.userId == user.id
//    userNote.isPublic == noteCreateDto.isPublic
//    }
//}



//@ToDo: сделать, чтобы можно было достать принципл из секьюрити , погуглить как в тестах спок получить принципл


