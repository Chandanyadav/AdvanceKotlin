package com.example.data.repositories.users

import com.example.domain.entities.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun getUsers(): Flow<List<UserInfo>>
}