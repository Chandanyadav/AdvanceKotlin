package com.example.data.repositories.users


import android.util.Log
import com.example.data.db.UserDao
import com.example.data.mappers.UserEntityMapper
import com.example.domain.common.Result
import com.example.domain.entities.UserInfo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocalDataSourceImpl(
    private val userDao: UserDao,
    private val dispatcher: CoroutineDispatcher,
    private val userEntityMapper: UserEntityMapper
) : UserLocalDataSource {

    override suspend fun saveUsers(userInfo: List<UserInfo>) =
        withContext(dispatcher) {
            userDao.saveUsers(userEntityMapper.toUserEntity(userInfo))
        }

    override suspend fun getUsers(): Flow<List<UserInfo>> =
        withContext(dispatcher) {
            val savedUsersFlow = userDao.getSavedUser()

            return@withContext savedUsersFlow.map { list ->
                list.map { element ->
                    userEntityMapper.toUserInfo(element)
                }
            }
        }
}