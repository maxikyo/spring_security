package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserNote
import com.holovanovmax.springboot_security_api.rest_api.model.userNote.NoteUpdateDto

interface UserNoteService {

    List<UserNote> getPublicUserNote()

    List<UserNote> getAllByUserIdAndPublicStatus(String userId, boolean isPublic)

    List<UserNote> getByUserId(String userId)

    UserNote create(UserNote userNote)

    UserNote update(UserNote updatedNote)

    void delete (String id)  // -> узнать если нужно добавить boolean

    UserNote getByIdAndUserId(String id, String userId)
}
