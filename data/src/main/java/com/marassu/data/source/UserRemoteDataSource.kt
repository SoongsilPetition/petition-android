package com.marassu.data.source

import com.marassu.data.service.UserService
import com.marassu.entity.user.UserLoginRequest
import com.marassu.entity.user.UserLoginResponse
import com.marassu.entity.user.UserRegisterRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit

class UserRemoteDataSource constructor(
    private val retrofitPair: Pair<Retrofit, Retrofit>
) {
    private val userService = retrofitPair.first.create(UserService::class.java)

    suspend fun postUserRegister(userRegisterRequest: UserRegisterRequest)
    = userService.postUserRegister(userRegisterRequest)

    suspend fun postUserLogin(userLoginRequest: UserLoginRequest)
    = userService.postUserLogin(userLoginRequest)
}