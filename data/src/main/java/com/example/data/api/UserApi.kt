package com.example.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("/api/users?page=2")
    suspend fun getUsers(@Query("q") page: Int): Response<UsersApiResponse>
}