package com.marassu.domain.usecase

import com.marassu.domain.repository.UserRepository
import com.marassu.entity.user.User
import com.marassu.entity.user.UserRegisterRequest
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class PostUserRegisterUseCase(private val userRepository: UserRepository) {
    suspend fun postUserRegister(userRegisterRequest: UserRegisterRequest): Flow<User> {
        return userRepository.postUserRegister(userRegisterRequest)
    }
}