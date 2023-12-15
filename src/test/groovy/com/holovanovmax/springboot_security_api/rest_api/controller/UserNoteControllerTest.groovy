package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.contextLoader.ContextLoader
import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.model.userNote.NoteCreateDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.core.context.SecurityContextHolder

import java.security.Principal

@WebMvcTest
class UserNoteControllerTest extends ContextLoader {

    @Autowired
    UserNoteController userNoteController

    @WithMockUser(username = "admin", password = "123", roles = ["ADMIN"])
    def "Should create a new note for an authorized user"() {
        given: "A valid NoteCreateDto"
        def noteCreateDto = new NoteCreateDto(content: "Hello world", isPublic: true)
        Principal principalS = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        def user = userService.registerNewUser(new User(
//                name: "admin",
//                password: "123",
//                role: "ADMIN"))

//        Principal principal = new Principal() -> создался новый класс)

        when: "The controller method is called with the NoteCreateDto and the principal"
        def principal = new Principal() {
            @Override
            String getName() {
                return "admin"
            }
        }
        def userNote = userNoteController.getNoteByUser(noteCreateDto, principal)

        then: "The userNote is not null and has the expected values"
        userNote != null
        userNote.content == noteCreateDto.content
        userNote.userId == user.id
        userNote.isPublic == noteCreateDto.isPublic
    }
}

//@ToDo: сделать, чтобы можно было достать принципл из секьюрити , погуглить как в тестах спок получить принципл


