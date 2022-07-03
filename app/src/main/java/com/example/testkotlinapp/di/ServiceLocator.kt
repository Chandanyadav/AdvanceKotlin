package com.example.testkotlinapp.di

import android.content.Context
import com.example.data.api.NetworkModule
import com.example.data.db.UserDatabase
import com.example.data.mappers.UserApiResponseMapper
import com.example.data.mappers.UserEntityMapper
import com.example.data.repositories.users.UserLocalDataSource
import com.example.data.repositories.users.UserLocalDataSourceImpl
import com.example.data.repositories.users.RemoteDataSourceImpl
import com.example.data.repositories.users.RepositoryImpl
import kotlinx.coroutines.Dispatchers

object ServiceLocator {
    private var database: UserDatabase? = null
    private var BASE_URL: String = "https://reqres.in/"
    private val networkModule by lazy {
        NetworkModule()
    }
    private val userEntityMapper by lazy {
        UserEntityMapper()
    }

    @Volatile
    var repository: RepositoryImpl? = null

    fun provideusersRepository(context: Context): RepositoryImpl {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return repository ?: createUsersRepository(context)
        }
    }

    private fun createUsersRepository(context: Context): RepositoryImpl {
        val newRepo =
            RepositoryImpl(
                createUsersLocalDataSource(context),
                RemoteDataSourceImpl(
                    networkModule.createUserApi(BASE_URL),
                    UserApiResponseMapper()
                )
            )
        repository = newRepo
        return newRepo
    }

    private fun createUsersLocalDataSource(context: Context): UserLocalDataSource {
        val database = database ?: createDataBase(context)
        return UserLocalDataSourceImpl(
            database.userDao(),
            Dispatchers.IO,
            userEntityMapper
        )
    }

    private fun createDataBase(context: Context): UserDatabase {
        val result = UserDatabase.getDatabase(context)
        database = result
        return result
    }
}