package com.example.data.repositories.users

import com.example.domain.common.Result
import com.example.domain.entities.User
import com.example.domain.entities.UserDetail

interface RemoteDataSource {
    suspend fun getUsers(page: Int): Result<User>

    suspend fun getUserDetail(page: Int): Result<UserDetail>
}