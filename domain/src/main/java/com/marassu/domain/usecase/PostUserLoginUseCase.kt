package com.marassu.domain.usecase

import com.marassu.domain.repository.UserRepository
import com.marassu.entity.user.UserLoginRequest
import kotlinx.coroutines.flow.Flow

class PostUserLoginUseCase(private val userRepository: UserRepository) {
    suspend fun postUserLogin(userLoginRequest: UserLoginRequest): Flow<String> {
        return userRepository.postUserLogin(userLoginRequest)
    }
}