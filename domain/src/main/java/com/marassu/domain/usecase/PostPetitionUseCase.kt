package com.marassu.domain.usecase

import com.marassu.domain.repository.PetitionRepository
import com.marassu.entity.petition.PetitionRequest

class PostPetitionUseCase(
    private val petitionRepository: PetitionRepository
) {
    suspend fun postPetition(petitionRequest: PetitionRequest)
    = petitionRepository.postPetition(petitionRequest)
}