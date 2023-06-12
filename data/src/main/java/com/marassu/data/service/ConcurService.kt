package com.marassu.data.service

import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.Concur
import com.marassu.entity.concur.ConcurRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ConcurService {
    @GET("/concur")
    suspend fun getConcurList(
        @Query("petitionId") petitionId: Long,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Response<ArrayList<Concur>>

    @POST("/concur")
    suspend fun postConcur(@Body concurRequest: ConcurRequest): Response<Concur>

    @GET("/user/concur")
    suspend fun getUserConcurList(
        @Query("userId") userId: Int,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Response<ArrayList<Concur>>
}