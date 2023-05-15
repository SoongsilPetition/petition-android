package com.marassu.domain.repository

import com.marassu.entity.user.UserLoginRequest
import com.marassu.entity.user.UserRegisterRequest

interface UserRepository {
    suspend fun postUserRegister(userRegisterRequest: UserRegisterRequest)

    suspend fun postUserLogin(userLoginRequest: UserLoginRequest)
}