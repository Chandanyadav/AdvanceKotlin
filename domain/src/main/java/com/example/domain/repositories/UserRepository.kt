package com.example.domain.repositories

import com.example.domain.common.Result
import com.example.domain.entities.User
import com.example.domain.entities.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getRemoteUsers(page: Int): Result<User>

    suspend fun getUsers(): Flow<List<UserInfo>>

}