package com.example.domain.usecases

import com.example.domain.repositories.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(page: Int) = userRepository.getRemoteUsers(page)
}