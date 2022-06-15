package com.example.data.repositories.classxyz

import com.example.domain.common.Result
import com.example.domain.entities.User

interface UserRemoteDataSource {
    suspend fun getUsers(page: Int): Result<User>
}