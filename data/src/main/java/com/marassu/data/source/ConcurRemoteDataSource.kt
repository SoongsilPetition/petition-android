package com.marassu.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marassu.data.service.ConcurService
import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.Concur
import com.marassu.entity.concur.ConcurRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit

class ConcurRemoteDataSource(
    private val retrofitPair: Pair<Retrofit, Retrofit>
) {
    private val publicConcurService: ConcurService = retrofitPair.first.create(ConcurService::class.java)
    private val tokenConcurService: ConcurService = retrofitPair.second.create(ConcurService::class.java)

    fun getConcurList(
        petitionId: Long,
        page: Int = 1,
        size: Int = 10
    ): Flow<PagingData<Concur>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),

            pagingSourceFactory = {
                ConcurPageSource(service = publicConcurService, petitionId)
            }
        ).flow
    }

    suspend fun postConcur(concurRequest: ConcurRequest) =
        tokenConcurService.postConcur(concurRequest)

    suspend fun getUserConcurList(
        userId: Int,
        page: Int = 1,
        size: Int = 10
    ) = publicConcurService.getUserConcurList(userId, page, size)
}