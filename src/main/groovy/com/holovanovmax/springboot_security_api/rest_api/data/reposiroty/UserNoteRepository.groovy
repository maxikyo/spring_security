package com.holovanovmax.springboot_security_api.rest_api.data.reposiroty

import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserNote
import org.springframework.data.mongodb.repository.MongoRepository

interface UserNoteRepository extends MongoRepository <UserNote, String>{

        List<UserNote> findAllByIsPublic(boolean isPublic)

        List<UserNote> findAllByUserIdAndIsPublic(String userId, boolean isPublic)

        List<UserNote> findAllByUserId(String userId)
}