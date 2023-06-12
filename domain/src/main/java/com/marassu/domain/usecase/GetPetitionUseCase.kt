package com.marassu.domain.usecase

import com.marassu.domain.repository.PetitionRepository
import javax.inject.Inject

class GetPetitionUseCase @Inject constructor(private val petitionRepository: PetitionRepository) {
    suspend fun getPetition(petitionId: Long) = petitionRepository.getPetition(petitionId)
}