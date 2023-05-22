package com.marassu.domain.usecase

import com.marassu.domain.repository.PetitionRepository

class GetPetitionUseCase(private val petitionRepository: PetitionRepository) {
    suspend fun getPetition(petitionId: Long) = petitionRepository.getPetition(petitionId)
}