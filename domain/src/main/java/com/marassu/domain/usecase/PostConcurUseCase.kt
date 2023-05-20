package com.marassu.domain.usecase

import com.marassu.domain.repository.ConcurRepository
import com.marassu.entity.concur.ConcurRequest

class PostConcurUseCase(
    private val postConcurRepository: ConcurRepository
) {
    suspend fun postConcur(concurRequest: ConcurRequest) =
        postConcurRepository.postConcur(concurRequest)
}