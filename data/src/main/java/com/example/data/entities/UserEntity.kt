package com.example.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avtaar: String
)