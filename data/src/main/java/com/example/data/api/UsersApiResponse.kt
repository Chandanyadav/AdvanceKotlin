package com.example.data.api

import com.example.domain.entities.UserInfo
import com.squareup.moshi.Json


data class UsersApiResponse(
    @field:Json(name = "page")
    val page : Int,
    @field:Json(name = "data")
    val data: List<ApiUserInfo>
)

data class ApiUserInfo(
    val id: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val avatar: String
)
