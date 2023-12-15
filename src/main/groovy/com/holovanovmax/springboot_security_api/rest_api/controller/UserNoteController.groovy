package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserNote
import com.holovanovmax.springboot_security_api.rest_api.model.userNote.NoteCreateDto
import com.holovanovmax.springboot_security_api.rest_api.model.userNote.NoteUpdateDto
import com.holovanovmax.springboot_security_api.rest_api.service.BalanceService
import com.holovanovmax.springboot_security_api.rest_api.service.UserNoteService
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

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
    ) {
        User user = userService.findByPrincipal(principal)
        if (!user){
            throw new IllegalArgumentException("New not can be created only for authorization users")
        }
        if (user) {
            UserNote newUserNote = userNoteService.create(new UserNote(
                    content: dto.content,
                    userId: user.id,
                    isPublic: dto.isPublic
            ))
            return newUserNote
        } else {
            throw new IllegalArgumentException("User didn't found. Create is not possible")
        }
    }

    @PreAuthorize('hasAuthority("ADMIN") or hasAuthority("USER")')
    @GetMapping("/api/notes/get/all/my")
    List<UserNote> getAllMyNotes(Principal principal){
        User user = userService.findByPrincipal(principal)
        if (user) {
            return userNoteService.getByUserId(user.id)
        } else {
            throw new IllegalArgumentException("User did not found")
        }
    }

    @PreAuthorize('hasAuthority("ADMIN") or hasAuthority("USER")')
    @DeleteMapping("/api/notes/delete/{id}")
    ResponseEntity deleteNoteById(@PathVariable String id,
                                    Principal principal) {
        User user = userService.findByPrincipal(principal)
        userNoteService.checkNoteOwner(id, user.id)
        userNoteService.delete(id)
    }

    @PreAuthorize('hasAuthority("ADMIN") or hasAuthority("USER")')
    @PostMapping("/api/notes/update/my")
    UserNote updateNote(@RequestBody NoteUpdateDto dto,
                        Principal principal
                        ) {
        User user = userService.findByPrincipal(principal)
        userNoteService.checkNoteOwner(dto.id, user.id)
            userNoteService.update(new UserNote(
                    id: dto.id,
                    content: dto.content,
                    userId: user.id,
                    isPublic: dto.isPublic
            ))
    }
}



