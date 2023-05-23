package com.marassu.data.source

import com.marassu.data.service.ConcurService
import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.ConcurRequest
import retrofit2.Retrofit

class ConcurRemoteDataSource(
    private val retrofitPair: Pair<Retrofit, Retrofit>
) {
    private val publicConcurService: ConcurService = retrofitPair.first.create(ConcurService::class.java)
    private val tokenConcurService: ConcurService = retrofitPair.second.create(ConcurService::class.java)

    suspend fun getConcurList(
        petitionId: Int,
        agreementStatus: AgreementStatus = AgreementStatus.AGREE,
        page: Int = 1,
        size: Int = 10
    ) = publicConcurService.getConcurList(petitionId, agreementStatus, page, size)

    suspend fun postConcur(concurRequest: ConcurRequest) =
        tokenConcurService.postConcur(concurRequest)

    suspend fun getUserConcurList(
        userId: Int,
        page: Int = 1,
        size: Int = 10
    ) = publicConcurService.getUserConcurList(userId, page, size)
}