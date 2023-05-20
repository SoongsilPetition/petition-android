package com.marassu.data.service

import com.marassu.entity.petition.Petition
import com.marassu.entity.petition.PetitionRequest
import com.marassu.entity.petition.Sort
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PetitionService {
    @GET("/petition")
    suspend fun getPetitionList(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
        @Query("sort") sort: Sort = Sort.CREATED_AT,
        @Query("category") category: String
    ): Response<ArrayList<Petition>>

    @POST("/petition")
    suspend fun postPetition(@Body petitionRequest: PetitionRequest): Response<Petition>

    @GET("/petition/{petitionId}")
    suspend fun getPetition(@Path("petitionId") petitionId: Long): Response<Petition>
}