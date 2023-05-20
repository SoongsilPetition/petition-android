package com.marassu.data.repositoryImpl

import com.marassu.data.source.ConcurRemoteDataSource
import com.marassu.domain.repository.ConcurRepository
import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.Concur
import com.marassu.entity.concur.ConcurRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConcurRepositoryImpl @Inject constructor(
    private val concurRemoteDataSource: ConcurRemoteDataSource
): ConcurRepository {
    override suspend fun getConcurList(
        petitionId: Int,
        agreementStatus: AgreementStatus,
        page: Int,
        size: Int
    ): Flow<ArrayList<Concur>> {
        return flow {
            concurRemoteDataSource.getConcurList(petitionId, agreementStatus, page, size).body()?.let {
                emit(it)
            }
        }
    }

    override suspend fun postConcur(concurRequest: ConcurRequest): Flow<Concur> {
        return flow {
            concurRemoteDataSource.postConcur(concurRequest).body()?.let {
                emit(it)
            }
        }
    }

    override suspend fun getUserConcurList(
        userId: Int,
        page: Int,
        size: Int
    ): Flow<ArrayList<Concur>> {
        return flow {
            concurRemoteDataSource.getUserConcurList(userId, page, size).body()?.let {
                emit(it)
            }
        }
    }
}