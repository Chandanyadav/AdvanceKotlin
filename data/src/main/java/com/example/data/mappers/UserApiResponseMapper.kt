package com.example.data.mappers

import android.util.Log
import com.example.data.api.UsersApiResponse
import com.example.domain.entities.User
import com.example.domain.entities.UserInfo

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
}