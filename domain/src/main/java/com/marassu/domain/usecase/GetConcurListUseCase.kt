package com.marassu.domain.usecase

import com.marassu.domain.repository.ConcurRepository
import com.marassu.entity.concur.AgreementStatus

class GetConcurListUseCase(
    private val concurRepository: ConcurRepository
) {
    suspend fun getConcurList(
        petitionId: Int,
        agreementStatus: AgreementStatus = AgreementStatus.AGREE,
        page: Int = 1,
        size: Int = 10
    ) = concurRepository.getConcurList(petitionId, agreementStatus, page, size)
}