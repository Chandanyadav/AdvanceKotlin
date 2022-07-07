package com.example.domain.usecases

import com.example.domain.entities.UserInfo
import com.example.domain.repositories.UserRepository

class SaveUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userInfo: List<UserInfo>) = userRepository.saveUsers(userInfo)
}