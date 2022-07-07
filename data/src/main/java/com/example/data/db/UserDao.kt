package com.example.data.db

import androidx.room.*
import com.example.data.entities.UserEntity
import com.example.domain.entities.UserInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(user: List<UserEntity>)

    @Query("SELECT * FROM user")
    fun getSavedUser(): Flow<List<UserEntity>>

}