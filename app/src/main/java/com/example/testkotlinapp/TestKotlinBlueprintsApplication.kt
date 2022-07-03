package com.example.testkotlinapp

import android.app.Application
import com.example.data.repositories.users.RepositoryImpl
import com.example.domain.usecases.GetLocalUserUseCase
import com.example.domain.usecases.GetUserDetailUseCase
import com.example.domain.usecases.GetUserUseCase
import com.example.domain.usecases.SaveUserUseCase
import com.example.testkotlinapp.di.ServiceLocator
import timber.log.Timber

class TestKotlinBlueprintsApplication : Application() {
    private val repositoryImpl: RepositoryImpl
        get() = ServiceLocator.provideusersRepository(this)

    val saveUserUseCase: SaveUserUseCase
        get() = SaveUserUseCase(repositoryImpl)

    val getLocalUserUseCase: GetLocalUserUseCase
        get() = GetLocalUserUseCase(repositoryImpl)

    val getUserUseCase: GetUserUseCase
        get() = GetUserUseCase(repositoryImpl)

    val getUserDetailUseCase: GetUserDetailUseCase
        get() = GetUserDetailUseCase(repositoryImpl)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}