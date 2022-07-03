package com.example.data.repositories.users

import android.util.Log
import com.example.data.api.UserApi
import com.example.data.mappers.UserApiResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.domain.common.Result
import com.example.domain.entities.User
import com.example.domain.entities.UserDetail


class RemoteDataSourceImpl(
    private val service: UserApi,
    private val mapper: UserApiResponseMapper,
) : RemoteDataSource {
    override suspend fun getUsers(page: Int): Result<User> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getUsers(page)

                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toUser(response.body()!!))
                } else {
                    Log.d("APIResponseError ", response.message())
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.d("APIResponseException ", e.toString())
                return@withContext Result.Error(e)
            }
        }

    override suspend fun getUserDetail(userId: Int): Result<UserDetail> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getUserDetails(userId)

                if(response.isSuccessful) {
                    return@withContext Result.Success(mapper.toUserDetail(response.body()!!))
                }else{
                    Log.d("APIResponseError ", response.message())
                    return@withContext Result.Error(Exception(response.message()))
                }
            }catch (e: Exception) {
                Log.d("APIResponseException ", e.toString())
                return@withContext Result.Error(e)

            }
        }
}