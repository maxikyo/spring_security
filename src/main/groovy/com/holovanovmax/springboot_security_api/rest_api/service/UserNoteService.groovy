package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserNote

interface UserNoteService {

    List<UserNote> getPublicUserNote()

    List<UserNote> getPrivateUserNote(String id,String userId)

    UserNote getUserNoteById(String userId)

    UserNote createNote(UserNote userNote)

    UserNote updateUserNote(String id, UserNote updatedNote)

    void deleteUserNote(String id)
}