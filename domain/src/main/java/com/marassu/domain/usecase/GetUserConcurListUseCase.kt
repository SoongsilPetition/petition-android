package com.marassu.domain.usecase

import com.marassu.domain.repository.ConcurRepository

class GetUserConcurListUseCase(
    private val concurRepository: ConcurRepository
) {
    suspend fun getUserConcurList(
        userId: Int,
        page: Int = 1,
        size: Int = 10
    ) = concurRepository.getUserConcurList(userId, page, size)
}