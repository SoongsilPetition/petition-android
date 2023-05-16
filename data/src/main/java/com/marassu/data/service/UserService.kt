package com.marassu.data.service

import com.marassu.entity.user.User
import com.marassu.entity.user.UserRegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user/register")
    suspend fun postUserRegister(@Body userRegisterRequest: UserRegisterRequest): User
}