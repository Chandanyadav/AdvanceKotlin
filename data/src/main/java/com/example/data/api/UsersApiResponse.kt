package com.example.data.api

import com.squareup.moshi.Json

class UsersApiResponse(val items: Item)

data class Item(
    @field:Json(name = "page")
    val page : String,
    @field:Json(name = "data")
    val userInfo: ApiUserInfo
)

data class ApiUserInfo(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avtar: String
)
