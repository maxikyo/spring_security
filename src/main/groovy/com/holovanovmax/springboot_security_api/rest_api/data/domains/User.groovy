package com.holovanovmax.springboot_security_api.rest_api.data.domains


import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
//сущности датабазы в домены
@Document("users")
class User {

    @Id
    String id

    String name

    String password

    String role

    @Version
    Long version

    //ToDo
    //У пользователя должны быть заметки, например две заметки одна паблик, одна приват, чтобы паблик могли видеть все
    //а приват только пользователь(владелец) после авторизации, сделать это через секьюрити
    //совет: пару вопросов про research. Критиковать свой проект, чтобы хотелось его улучшить,
    // критиковать и пытаться улучшить свой код
    //задачу нужно-можно сделать за 2 вечера
    //прогуглить каждый этап отображения заметок пользователя, как вытащить из базы, спринсг секьюрити, getUsersNotes.
    //разбить задачу на несколько мелких групп('этапов), как по пользователю в спринге получать какую-то информацию, чем меньше кода - тем проще
    //

}
