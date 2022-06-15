package com.example.data.repositories.classxyz

import com.example.domain.entities.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun getUsers(): Flow<List<UserInfo>>
}