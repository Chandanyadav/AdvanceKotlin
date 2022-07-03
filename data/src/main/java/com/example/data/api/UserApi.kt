package com.example.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("/api/users?")
    suspend fun getUsers(@Query("page") page: Int): Response<UsersApiResponse>

    @GET("/api/users/{userId}")
    suspend fun getUserDetails(@Path("userId") userId: Int): Response<UserDetailAPIResponse>
}