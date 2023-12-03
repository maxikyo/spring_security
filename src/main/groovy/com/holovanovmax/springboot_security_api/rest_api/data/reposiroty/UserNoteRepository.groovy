package com.holovanovmax.springboot_security_api.rest_api.data.reposiroty

import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserNote
import org.springframework.data.mongodb.repository.MongoRepository

interface UserNoteRepository extends MongoRepository <UserNote, String>{
        List<UserNote> findByIsPublic(boolean isPublic)
        List<UserNote> findByUserIdAndIsPublic(String userId, boolean isPublic)
        Optional<UserNote> getNoteByUserId(String userId)
}