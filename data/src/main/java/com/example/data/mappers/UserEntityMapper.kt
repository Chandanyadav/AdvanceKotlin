package com.example.data.mappers

import com.example.data.entities.UserEntity
import com.example.domain.entities.User
import com.example.domain.entities.UserInfo

class UserEntityMapper {
    fun toUserEntity(user: User): UserEntity {
        return UserEntity(
            id = user.data[0].id,
            firstName = user.data[0].firstName,
            lastName = user.data[0].lastName,
            email = user.data[0].email,
            avtar = user.data[0].id
        )
    }

    fun toUserInfo(userEntity: UserEntity): UserInfo {
        return UserInfo(
            userEntity.id, userEntity.firstName, userEntity.lastName, userEntity.email, userEntity.avtar
        )
    }
}