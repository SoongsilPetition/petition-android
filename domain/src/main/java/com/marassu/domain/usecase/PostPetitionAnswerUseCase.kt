package com.marassu.domain.usecase

import com.marassu.domain.repository.PetitionAnswerRepository
import com.marassu.entity.petition_answer.PetitionAnswerRequest

class PostPetitionAnswerUseCase(
    private val repository: PetitionAnswerRepository
) {
    suspend fun postPetitionAnswer(petitionAnswerRequest: PetitionAnswerRequest) =
        repository.postPetitionAnswer(petitionAnswerRequest)
}