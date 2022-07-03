package com.example.data.api

import com.squareup.moshi.Json

data class UserDetailAPIResponse(
    @field:Json(name = "data")
    val data: UserApiInfo,

    @field:Json(name = "support")
    val support: UserSupportApiInfo
)

data class UserApiInfo(
    val id: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val avatar: String
)

data class UserSupportApiInfo(
    val url: String,
    val text: String,
)