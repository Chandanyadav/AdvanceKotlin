package com.example.domain.usecases

import com.example.domain.repositories.UserRepository

class GetUserDetailUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userID: Int) = userRepository.getUserDetail(userID)
}