package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserNote
import com.holovanovmax.springboot_security_api.rest_api.data.reposiroty.UserNoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserNoteServiceImpl implements UserNoteService{

    @Autowired
    private UserServiceImpl userService

    @Autowired
    private UserNoteRepository userNoteRepository

    @Override
    List<UserNote> getPublicUserNote() {
        userNoteRepository.findAllByIsPublic(true)
    }

    @Override
    List<UserNote> getAllByUserIdAndPublicStatus(String userId, boolean isPublic) {
        userNoteRepository.findAllByUserIdAndIsPublic(userId, isPublic)
    }

    @Override
    List<UserNote> getByUserId(String userId) {
        userNoteRepository.findAllByUserId(userId)
    }

    @Override
    UserNote create(UserNote userNote) {
        userNoteRepository.save(userNote)
    }


    @Override
    UserNote update(UserNote updatedNote) {
        UserNote note = userNoteRepository.findById(updatedNote.id).orElse(null)
        if (!note){
            throw new IllegalArgumentException("Такой заметки не существует")
        }
        note.content = updatedNote.content
        note.isPublic = updatedNote.isPublic
        userNoteRepository.save(note)
    }

    @Override
    void delete(String id) {
        userNoteRepository.deleteById(id)
    }

    @Override
    UserNote getByIdAndUserId(String id, String userId) {
        userNoteRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new IllegalArgumentException("User with ${userId} or note with ${id} didn't found"))
    }

    @Override
    Boolean existsByIdAndUserId(String id, String userId) {
        userNoteRepository.existsByIdAndUserId(id, userId)
    }


    @Override
    void checkNoteOwner(String id, String userId) {
        if(!existsByIdAndUserId(id, userId)) {
            throw new IllegalArgumentException(
                    "User with ${userId} or note with ${id} didn't found")
        }
    }
}
