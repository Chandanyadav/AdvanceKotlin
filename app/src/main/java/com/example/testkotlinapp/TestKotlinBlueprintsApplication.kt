package com.example.testkotlinapp

import android.app.Application
import com.example.data.repositories.classxyz.UserRepositoryImpl
import com.example.domain.usecases.GetUserUseCase
import com.example.testkotlinapp.di.ServiceLocator
import timber.log.Timber

class TestKotlinBlueprintsApplication : Application() {
    private val userRepositoryImpl: UserRepositoryImpl
        get() = ServiceLocator.provideusersRepository(this)

    val getUserUseCase: GetUserUseCase
        get() = GetUserUseCase(userRepositoryImpl)


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}