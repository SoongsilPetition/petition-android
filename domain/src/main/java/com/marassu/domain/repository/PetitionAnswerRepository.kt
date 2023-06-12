package com.marassu.domain.repository

import com.marassu.entity.petition_answer.PetitionAnswer
import com.marassu.entity.petition_answer.PetitionAnswerRequest
import kotlinx.coroutines.flow.Flow

interface PetitionAnswerRepository {
    suspend fun postPetitionAnswer(petitionAnswerRequest: PetitionAnswerRequest): Flow<PetitionAnswer>

    suspend fun getPetitionAnswer(petitionId: Long): Flow<PetitionAnswer>

    suspend fun deletePetitionAnswer(petitionId: Int): Flow<Unit>

    suspend fun modifyPetitionAnswer(
        petitionId: Int,
        petitionAnswerRequest: PetitionAnswerRequest
    ): Flow<PetitionAnswer>
}