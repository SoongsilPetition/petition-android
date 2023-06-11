package com.marassu.data.util

import androidx.paging.PagingData
import com.marassu.entity.petition.Petition
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

object CommonAPILogic {
    suspend fun <T> checkError(response: Response<T>): Flow<T> {
        return flow {
            if(response.isSuccessful) {
                response.body()?.let {
                    emit(it)
                }
            } else {
                val code = response.code()
                val errorMessage = response.errorBody()?.string()
                throw HttpException(code, errorMessage)
            }
        }
    }
}