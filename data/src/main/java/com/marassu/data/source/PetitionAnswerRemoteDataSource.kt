package com.marassu.data.source

import com.marassu.data.service.PetitionAnswerService
import com.marassu.entity.petition_answer.PetitionAnswerRequest
import retrofit2.Retrofit

class PetitionAnswerRemoteDataSource(
    private val retrofitPair: Pair<Retrofit, Retrofit>
) {
    private val publicPetitionAnswerService: PetitionAnswerService =
        retrofitPair.first.create(PetitionAnswerService::class.java)
    private val tokenPetitionAnswerService: PetitionAnswerService =
        retrofitPair.second.create(PetitionAnswerService::class.java)

    suspend fun postPetitionAnswer(petitionAnswerRequest: PetitionAnswerRequest) =
        tokenPetitionAnswerService.postPetitionAnswer(petitionAnswerRequest)

    suspend fun getPetitionAnswer(petitionId: Long) =
        publicPetitionAnswerService.getPetitionAnswer(petitionId)

    suspend fun deletePetitionAnswer(petitionId: Int) =
        tokenPetitionAnswerService.deletePetitionAnswer(petitionId)

    suspend fun modifyPetitionAnswer(
        petitionId: Int,
        petitionAnswerRequest: PetitionAnswerRequest
    ) = tokenPetitionAnswerService.modifyPetitionAnswer(petitionId, petitionAnswerRequest)
}