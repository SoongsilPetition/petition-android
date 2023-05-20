package com.marassu.domain.usecase

import com.marassu.domain.repository.PetitionAnswerRepository

class DeletePetitionAnswerUseCase(
    private val repository: PetitionAnswerRepository
) {
    suspend fun deletePetitionAnswer(petitionId: Int) =
        repository.deletePetitionAnswer(petitionId)
}