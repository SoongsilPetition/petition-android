package com.marassu.domain.repository

import androidx.paging.PagingData
import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.Concur
import com.marassu.entity.concur.ConcurRequest
import kotlinx.coroutines.flow.Flow

interface ConcurRepository {
    suspend fun getConcurList(
        petitionId: Long,
        page: Int = 1,
        size: Int = 10
    ): Flow<List<Concur>>

    suspend fun postConcur(concurRequest: ConcurRequest): Flow<Concur>

    suspend fun getUserConcurList(
        userId: Int,
        page: Int = 1,
        size: Int = 10
    ): Flow<ArrayList<Concur>>
}