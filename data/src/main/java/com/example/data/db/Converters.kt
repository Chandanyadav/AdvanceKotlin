package com.example.data.db

import androidx.room.TypeConverter
import com.example.domain.entities.User
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromUsersList(value: List<User>?) = Gson().toJson(value)

    @TypeConverter
    fun toUsersList(value: String) = Gson().fromJson(value, Array<User>::class.java).toList()
}