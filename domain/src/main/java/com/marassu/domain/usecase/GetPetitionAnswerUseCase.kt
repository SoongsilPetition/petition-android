package com.marassu.domain.usecase

import com.marassu.domain.repository.PetitionAnswerRepository

class GetPetitionAnswerUseCase(
    private val repository: PetitionAnswerRepository
) {
    suspend fun getPetitionAnswer(petitionId: Long) =
        repository.getPetitionAnswer(petitionId)
}