package com.marassu.data.source

import com.marassu.data.service.ConcurService
import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.ConcurRequest
import retrofit2.Retrofit

class ConcurRemoteDataSource(
    private val retrofit: Retrofit
) {
    private val concurService: ConcurService = retrofit.create(ConcurService::class.java)

    suspend fun getConcurList(
        petitionId: Int,
        agreementStatus: AgreementStatus = AgreementStatus.AGREE,
        page: Int = 1,
        size: Int = 10
    ) = concurService.getConcurList(petitionId, agreementStatus, page, size)

    suspend fun postConcur(concurRequest: ConcurRequest) =
        concurService.postConcur(concurRequest)

    suspend fun getUserConcurList(
        userId: Int,
        page: Int = 1,
        size: Int = 10
    ) = concurService.getUserConcurList(userId, page, size)
}