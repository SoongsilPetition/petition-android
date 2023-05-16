package com.marassu.data.source

import com.marassu.data.service.UserService
import com.marassu.entity.user.User
import com.marassu.entity.user.UserRegisterRequest
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userService: UserService
) {
    suspend fun postUserRegister(userRegisterRequest: UserRegisterRequest): User
    = userService.postUserRegister(userRegisterRequest)
}