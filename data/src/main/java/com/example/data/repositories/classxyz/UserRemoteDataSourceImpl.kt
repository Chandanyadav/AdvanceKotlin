package com.example.data.repositories.classxyz

import com.example.data.api.UserApi
import com.example.data.mappers.UserApiResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.domain.common.Result
import com.example.domain.entities.User


class UserRemoteDataSourceImpl(
    private val service: UserApi,
    private val mapper: UserApiResponseMapper
) : UserRemoteDataSource {
    override suspend fun getUsers(page: Int): Result<User> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getUsers(page)
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toVolumeList(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
}