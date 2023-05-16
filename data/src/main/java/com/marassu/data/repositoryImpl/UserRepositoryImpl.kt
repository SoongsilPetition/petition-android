package com.marassu.data.repositoryImpl

import com.marassu.data.source.UserRemoteDataSource
import com.marassu.domain.repository.UserRepository
import com.marassu.entity.user.User
import com.marassu.entity.user.UserLoginRequest
import com.marassu.entity.user.UserRegisterRequest
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun postUserRegister(userRegisterRequest: UserRegisterRequest): User {
        return userRemoteDataSource.postUserRegister(userRegisterRequest)
    }

    override suspend fun postUserLogin(userLoginRequest: UserLoginRequest) {
        TODO("Not yet implemented")
    }

}