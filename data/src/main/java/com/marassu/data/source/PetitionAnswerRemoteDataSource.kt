package com.marassu.data.source

import com.marassu.data.service.PetitionAnswerService
import com.marassu.entity.petition_answer.PetitionAnswerRequest
import retrofit2.Retrofit

class PetitionAnswerRemoteDataSource(
    private val retrofit: Retrofit
) {
    private val petitionAnswerService: PetitionAnswerService =
        retrofit.create(PetitionAnswerService::class.java)

    suspend fun postPetitionAnswer(petitionAnswerRequest: PetitionAnswerRequest) =
        petitionAnswerService.postPetitionAnswer(petitionAnswerRequest)

    suspend fun getPetitionAnswer(petitionId: Int) =
        petitionAnswerService.getPetitionAnswer(petitionId)

    suspend fun deletePetitionAnswer(petitionId: Int) =
        petitionAnswerService.deletePetitionAnswer(petitionId)

    suspend fun modifyPetitionAnswer(
        petitionId: Int,
        petitionAnswerRequest: PetitionAnswerRequest
    ) = petitionAnswerService.modifyPetitionAnswer(petitionId, petitionAnswerRequest)
}