package com.marassu.data.service

import com.marassu.entity.petition_answer.PetitionAnswer
import com.marassu.entity.petition_answer.PetitionAnswerRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PetitionAnswerService {
    @POST("/petition/answer")
    suspend fun postPetitionAnswer(@Body petitionAnswerRequest: PetitionAnswerRequest): Response<PetitionAnswer>

    @GET("/petition/{petitionId}/answer")
    suspend fun getPetitionAnswer(@Path("petitionId") petitionId: Int): Response<PetitionAnswer>

    @DELETE("/petition/{petitionId}/answer")
    suspend fun deletePetitionAnswer(@Path("petitionId") petitionId: Int): Response<Unit>

    @PATCH("/petition/{petitionId}/answer")
    suspend fun modifyPetitionAnswer(
        @Path("petitionId") petitionId: Int,
        @Body petitionAnswerRequest: PetitionAnswerRequest
    ): Response<PetitionAnswer>
}