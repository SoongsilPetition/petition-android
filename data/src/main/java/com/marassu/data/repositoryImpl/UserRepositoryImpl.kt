package com.marassu.data.repositoryImpl

import com.marassu.data.source.UserRemoteDataSource
import com.marassu.data.util.CommonAPILogic
import com.marassu.domain.repository.UserRepository
import com.marassu.entity.user.User
import com.marassu.entity.user.UserLoginRequest
import com.marassu.entity.user.UserLoginResponse
import com.marassu.entity.user.UserRegisterRequest
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun postUserRegister(userRegisterRequest: UserRegisterRequest): Flow<User> {
        return CommonAPILogic.checkError( userRemoteDataSource.postUserRegister(userRegisterRequest))
    }

    override suspend fun postUserLogin(userLoginRequest: UserLoginRequest): Flow<UserLoginResponse> {
        return CommonAPILogic.checkError(userRemoteDataSource.postUserLogin(userLoginRequest))
    }


}