package com.example.data.mappers

import com.example.data.entities.UserEntity
import com.example.domain.entities.User
import com.example.domain.entities.UserInfo

class UserEntityMapper {
    fun toUserEntity(userInfo: UserInfo): UserEntity {
        return UserEntity(
            id = userInfo.id,
            firstName = userInfo.firstName,
            lastName = userInfo.lastName,
            email = userInfo.email,
            avtar = userInfo.id
        )
    }

    fun toUserInfo(userEntity: UserEntity): UserInfo {
        return UserInfo(
            userEntity.id, userEntity.firstName, userEntity.lastName, userEntity.email, userEntity.avtar
        )
    }
}