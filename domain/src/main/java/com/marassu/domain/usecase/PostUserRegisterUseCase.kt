package com.marassu.domain.usecase

import com.marassu.domain.repository.UserRepository
import com.marassu.entity.user.User
import com.marassu.entity.user.UserRegisterRequest
import kotlinx.coroutines.*

class PostUserRegisterUseCase(private val userRepository: UserRepository) {
    operator fun invoke(
        userRegisterRequest: UserRegisterRequest,
        scope: CoroutineScope, onResult: (User) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                userRepository.postUserRegister(userRegisterRequest)
            }
            onResult(deferred.await())
        }
    }
}