package com.example.testkotlinapp.di

import android.content.Context
import com.example.data.api.NetworkModule
import com.example.data.db.UserDatabase
import com.example.data.mappers.UserApiResponseMapper
import com.example.data.mappers.UserEntityMapper
import com.example.data.repositories.classxyz.UserLocalDataSource
import com.example.data.repositories.classxyz.UserLocalDataSourceImpl
import com.example.data.repositories.classxyz.UserRemoteDataSourceImpl
import com.example.data.repositories.classxyz.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers

object ServiceLocator {
    private var database: UserDatabase? = null
    private val networkModule by lazy {
        NetworkModule()
    }
    private val userEntityMapper by lazy {
        UserEntityMapper()
    }

    @Volatile
    var userRepository: UserRepositoryImpl? = null

    fun provideusersRepository(context: Context): UserRepositoryImpl {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return userRepository ?: createUsersRepository(context)
        }
    }

    private fun createUsersRepository(context: Context): UserRepositoryImpl {
        val newRepo =
            UserRepositoryImpl(
                createUsersLocalDataSource(context),
                UserRemoteDataSourceImpl(
                    networkModule.createUserApi("https://www.googleapis.com/"),
                    UserApiResponseMapper()
                )
            )
        userRepository = newRepo
        return newRepo
        //TODO
        //BuildConfig.GOOGLE_APIS_ENDPOINT
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