package com.example.data.repositories.users

import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import com.example.domain.common.Result
import com.example.domain.entities.User
import com.example.domain.entities.UserDetail
import com.example.domain.entities.UserInfo


class RepositoryImpl(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : UserRepository {

    override suspend fun getRemoteUsers(page: Int): Result<User> {
        return remoteDataSource.getUsers(page)
    }

    override suspend fun getLocalUsers(): Flow<List<UserInfo>> {
        return localDataSource.getUsers()
    }

    override suspend fun saveUsers(usrInfo: UserInfo) {
        return localDataSource.saveUsers(usrInfo)
    }

    override suspend fun getUserDetail(userID: Int): Result<UserDetail> {
        return remoteDataSource.getUserDetail(userID)
    }

}