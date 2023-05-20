package com.marassu.data.source

import com.marassu.data.service.UserService
import com.marassu.entity.user.UserLoginRequest
import com.marassu.entity.user.UserRegisterRequest
import retrofit2.Retrofit

class UserRemoteDataSource constructor(
    private val retrofit: Retrofit
) {
    private val userService = retrofit.create(UserService::class.java)

    suspend fun postUserRegister(userRegisterRequest: UserRegisterRequest)
    = userService.postUserRegister(userRegisterRequest)

    suspend fun postUserLogin(userLoginRequest: UserLoginRequest)
    = userService.postUserLogin(userLoginRequest)
}