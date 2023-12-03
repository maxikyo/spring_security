package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.data.domains.User
import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserNote
import com.holovanovmax.springboot_security_api.rest_api.data.reposiroty.UserNoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserNoteServiceImpl implements UserNoteService{

    @Autowired
    UserServiceImpl userService

    @Autowired
    UserNoteRepository userNoteRepository

    @Override
    List<UserNote> getPublicUserNote() {
        userNoteRepository.findByIsPublic(true)
    }

    @Override
    List<UserNote> getPrivateUserNote(String id, String userId) {
        userNoteRepository.findByUserIdAndIsPublic(userId, false)
    }

    @Override
    UserNote getUserNoteById(String id) {
        userNoteRepository.findById(id).orElse(null )
    }

    @Override
    UserNote createNote(UserNote userNote) {
        userNoteRepository.save(userNote)
    }


    @Override
    UserNote updateUserNote(String id, UserNote updatedNote) {
        UserNote note = userNoteRepository.findById(id).orElse(null)
        if (note){
            throw new IllegalArgumentException("Такой заметки не существует")
        }
        note.content = updatedNote.content
        note.userId = updatedNote.userId
        userNoteRepository.save(note)
    }

    @Override
    void deleteUserNote(String id) {
        userNoteRepository.deleteById(id)
    }

    UserNote getUserNote(String userId){
        userNoteRepository.getNoteByUserId(userId).orElse(null)
    }
}
