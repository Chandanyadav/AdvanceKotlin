package com.example.data.db

import androidx.room.*
import com.example.data.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity)

    @Query("SELECT * FROM user")
    fun getSavedUser(): Flow<List<UserEntity>>

    @Delete
    suspend fun deleteUser(user: UserEntity)
}