package com.marassu.domain.repository

import com.marassu.entity.user.User
import com.marassu.entity.user.UserLoginRequest
import com.marassu.entity.user.UserRegisterRequest
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun postUserRegister(userRegisterRequest: UserRegisterRequest): Flow<User>

    suspend fun postUserLogin(userLoginRequest: UserLoginRequest): Flow<String>
}