package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.contextLoader.ContextLoader
import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserNote
import com.holovanovmax.springboot_security_api.rest_api.model.userInformation.TestPrincipal
import com.holovanovmax.springboot_security_api.rest_api.model.userNote.NoteCreateDto
import com.holovanovmax.springboot_security_api.rest_api.model.userNote.NoteUpdateDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithMockUser



class UserNoteControllerTest extends ContextLoader {

    @Autowired
    UserNoteController userNoteController

    @WithMockUser(authorities = ['ADMIN', 'USER'])

    def "Should create a new note for an authorized user"() {
        given: "A valid NoteCreateDto"
        User user = userService.registerNewUser(new User(
                name: "user",
                password: "123",
                role: "USER"))
        def noteCreateDto = new NoteCreateDto(content: "Hello world", isPublic: true)

        when: "The controller method is called with the NoteCreateDto and the principal"
        def userNote = userNoteController.getNoteByUser(noteCreateDto, new TestPrincipal(user.name))

        then: "The userNote is not null and has the expected values"
        userNote != null
        userNote.content == noteCreateDto.content
        userNote.userId == user.id
        userNote.isPublic == noteCreateDto.isPublic
    }

    @WithMockUser(authorities = ['ADMIN', 'USER'])
    def "Should update exist userNote"() {
        given: "A valid NoteCreateDto"
        User user = userService.registerNewUser(new User(
                name: "user1",
                password: "1234",
                role: "USER"))
        def UserNote = userNoteService.create(new UserNote(
                id: "1",
                userId: user.id,
                content: "new content",
                isPublic: true
        ))

        when: "The service method making update for exist note"
        def noteUpdateDto = new NoteUpdateDto(id: "1", content: "new Content", isPublic: false)
        def userNote = userNoteController.updateNote(noteUpdateDto, new TestPrincipal(user.name))

        then: "The userNote is not null and has the expected values"
        userNote != null
        userNote.content == noteUpdateDto.content
        userNote.userId == user.id
        userNote.isPublic == noteUpdateDto.isPublic
    }
}


//    def "Should create a new note for an authorized user"() {
//        given: "A valid NoteCreateDto"
////        def user = userService.registerNewUser(new User(
////                name: "user",
////                password: "123",
////                role: "USER"))
//        def noteCreateDto = new NoteCreateDto(content: "Hello world", isPublic: true)
//
//        when: "The controller method is called with the NoteCreateDto and the principal"
//        Principal principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        def userNote = userNoteController.getNoteByUser(noteCreateDto, principal)
//
//        then: "The userNote is not null and has the expected values"
//        userNote != null
//        userNote.content == noteCreateDto.content
//        userNote.userId == user.id
//        userNote.isPublic == noteCreateDto.isPublic
//    }
//}

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


