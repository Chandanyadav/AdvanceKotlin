package com.example.data.mappers

import com.example.data.entities.UserEntity
import com.example.domain.entities.User
import com.example.domain.entities.UserInfo

class UserEntityMapper {
    fun toUserEntity(userInfo: List<UserInfo>): List<UserEntity> {

        return userInfo.map {
            UserEntity(
                it.id,
                it.firstName,
                it.lastName,
                it.email,
                it.avatar,
            )
        }
    }

    fun toUserInfo(userEntity: UserEntity): UserInfo {
        return UserInfo(
            userEntity.id, userEntity.firstName, userEntity.lastName, userEntity.email, userEntity.avtaar
        )
    }
}