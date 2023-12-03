package com.holovanovmax.springboot_security_api.rest_api.controller

import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserNote
import com.holovanovmax.springboot_security_api.rest_api.model.userInformation.UserDto
import com.holovanovmax.springboot_security_api.rest_api.service.BalanceService
import com.holovanovmax.springboot_security_api.rest_api.service.UserNoteService
import com.holovanovmax.springboot_security_api.rest_api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class UserNoteController {

    @Autowired
    private UserService userService

    @Autowired
    private BalanceService balanceService

    @Autowired
    private UserNoteService userNoteService

    @GetMapping("/api/public_notes")
    List<UserNote> getAllNotes() {
        userNoteService.getPublicUserNote()
    }

//    @GetMapping("/api/public_notes")
//    List<UserNote> getNoteByUser(@PathVariable String id, String userId){
//        UserNote correctNote = userNoteService.getPrivateUserNote(id, userId)
//    }


}