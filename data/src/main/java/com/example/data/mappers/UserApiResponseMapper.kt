package com.example.data.mappers

import android.util.Log
import com.example.data.api.UserDetailAPIResponse
import com.example.data.api.UsersApiResponse
import com.example.domain.entities.User
import com.example.domain.entities.UserDetail
import com.example.domain.entities.UserInfo
import com.example.domain.entities.UserSupport

class UserApiResponseMapper {
    fun toUser(response: UsersApiResponse): User {
        return User(
            response.page,
            response.data.map {
              UserInfo(  it.id,
                it.first_name,
                it.last_name,
                it.email,
                it.avatar
              )
            }
        )
    }

    fun toUserDetail(response: UserDetailAPIResponse): UserDetail {
        return UserDetail(
            UserInfo(
                response.data.id,
                response.data.first_name,
                response.data.last_name,
                response.data.email,
                response.data.avatar
            ),
            UserSupport(
                response.support.url,
                response.support.text
            )
        )
    }
}