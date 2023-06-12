package com.marassu.domain.usecase

import com.marassu.domain.repository.UserRepository
import com.marassu.entity.user.UserLoginRequest
import com.marassu.entity.user.UserLoginResponse
import kotlinx.coroutines.flow.Flow

class PostUserLoginUseCase(private val userRepository: UserRepository) {
    suspend fun postUserLogin(userLoginRequest: UserLoginRequest): Flow<UserLoginResponse> {
        return userRepository.postUserLogin(userLoginRequest)
    }
}