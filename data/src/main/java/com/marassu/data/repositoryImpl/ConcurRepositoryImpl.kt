package com.marassu.data.repositoryImpl

import androidx.paging.PagingData
import com.marassu.data.source.ConcurRemoteDataSource
import com.marassu.data.util.CommonAPILogic
import com.marassu.domain.repository.ConcurRepository
import com.marassu.entity.concur.Concur
import com.marassu.entity.concur.ConcurRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConcurRepositoryImpl @Inject constructor(
    private val concurRemoteDataSource: ConcurRemoteDataSource
): ConcurRepository {
    override suspend fun getConcurList(petitionId: Long, page: Int, size: Int): Flow<List<Concur>> {
        return CommonAPILogic.checkError(concurRemoteDataSource.getConcurList(petitionId, page, size))
    }

    override suspend fun postConcur(concurRequest: ConcurRequest): Flow<Concur> {
        return CommonAPILogic.checkError(concurRemoteDataSource.postConcur(concurRequest))
    }

    override suspend fun getUserConcurList(
        userId: Int,
        page: Int,
        size: Int
    ): Flow<ArrayList<Concur>> {
        return CommonAPILogic.checkError(concurRemoteDataSource.getUserConcurList(userId, page, size))
    }
}