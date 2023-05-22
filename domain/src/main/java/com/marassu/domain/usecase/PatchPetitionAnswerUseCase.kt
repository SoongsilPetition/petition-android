package com.marassu.domain.usecase

import com.marassu.domain.repository.PetitionAnswerRepository
import com.marassu.entity.petition_answer.PetitionAnswerRequest

class PatchPetitionAnswerUseCase(
    private val repository: PetitionAnswerRepository
) {
    suspend fun modifyPetitionAnswer(
        petitionId: Int,
        petitionAnswerRequest: PetitionAnswerRequest
    ) = repository.modifyPetitionAnswer(petitionId, petitionAnswerRequest)
}