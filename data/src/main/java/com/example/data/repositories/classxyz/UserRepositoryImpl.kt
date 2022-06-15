package com.example.data.repositories.classxyz

import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import com.example.domain.common.Result
import com.example.domain.entities.User
import com.example.domain.entities.UserInfo


class UserRepositoryImpl(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getRemoteUsers(page: Int): Result<User> {
        return remoteDataSource.getUsers(page)
    }

    override suspend fun getUsers(): Flow<List<UserInfo>> {
        return localDataSource.getUsers()
    }

}