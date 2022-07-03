package com.example.domain.usecases

import com.example.domain.repositories.UserRepository

class GetLocalUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.getLocalUsers()
}