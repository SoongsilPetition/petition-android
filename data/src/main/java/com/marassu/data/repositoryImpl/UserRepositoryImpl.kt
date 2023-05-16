package com.marassu.data.repositoryImpl

import com.marassu.data.source.UserRemoteDataSource
import com.marassu.domain.repository.UserRepository
import com.marassu.entity.user.User
import com.marassu.entity.user.UserLoginRequest
import com.marassu.entity.user.UserRegisterRequest
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun postUserRegister(userRegisterRequest: UserRegisterRequest): Flow<User> {
        return  flow {
            userRemoteDataSource.postUserRegister(userRegisterRequest).body()?.let {
                emit(it)
            }
        }
    }

    override suspend fun postUserLogin(userLoginRequest: UserLoginRequest): Flow<Unit> {
        return flow {
            userRemoteDataSource.postUserLogin(userLoginRequest).body()?.let {
                emit(it)
            }
        }
    }

}