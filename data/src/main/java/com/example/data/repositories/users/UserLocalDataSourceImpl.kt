package com.example.data.repositories.users


import com.example.data.db.UserDao
import com.example.data.mappers.UserEntityMapper
import com.example.domain.entities.UserInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocalDataSourceImpl(
    private val userDao: UserDao,
    private val dispatcher: CoroutineDispatcher,
    private val userEntityMapper: UserEntityMapper
) : UserLocalDataSource {

    override suspend fun getUsers(): Flow<List<UserInfo>> {
        val savedUsersFlow = userDao.getSavedUser()
        return savedUsersFlow.map { list ->
            list.map { element ->
                userEntityMapper.toUserInfo(element)
            }
        }
    }
}