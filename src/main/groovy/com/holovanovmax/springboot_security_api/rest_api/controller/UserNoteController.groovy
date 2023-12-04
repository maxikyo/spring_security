package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserNote
import com.holovanovmax.springboot_security_api.rest_api.model.userNote.NoteCreateDto
import com.holovanovmax.springboot_security_api.rest_api.service.BalanceService
import com.holovanovmax.springboot_security_api.rest_api.service.UserNoteService
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

@RestController
class UserNoteController {

    @Autowired
    private UserService userService

    @Autowired
    private BalanceService balanceService

    @Autowired
    private UserNoteService userNoteService

    @GetMapping("/api/notes/get/all")
    List<UserNote> getAllNotes() {
        userNoteService.getPublicUserNote()
    }

    @PreAuthorize('hasAuthority("ADMIN") or hasAuthority("USER")')
    @PostMapping("/api/notes/create")
    UserNote getNoteByUser(
        @RequestBody NoteCreateDto dto,
            Principal principal
    ){
        User user = userService.findByPrincipal(principal)
        if (user){
            UserNote newUserNote = userNoteService.create(new UserNote(
                    content: dto.content,
                    userId: user.id,
                    isPublic: dto.isPublic
            ))
            return newUserNote
        }else{
            throw new IllegalArgumentException("User didn't found. Create is not possible")
        }
    }

    @PreAuthorize('hasAuthority("ADMIN") or hasAuthority("USER")')
    @GetMapping("/api/notes/get/all/my")
    List<UserNote> getAllMyNotes(
            Principal principal
    ){
        User user = userService.findByPrincipal(principal)
        if (user){
            return userNoteService.getByUserId(user.id)
        }else{
            throw new IllegalArgumentException("User did not found")
        }
    }

    @PreAuthorize('hasAuthority("ADMIN") or hasAuthority("USER")')
    @DeleteMapping("api/notes/delete/{id}")
    ResponseEntity deleteNoteById(@PathVariable String id){
        userNoteService.delete(id)
    }
}