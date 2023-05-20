package com.marassu.data.repositoryImpl

import com.marassu.data.source.PetitionRemoteDataSource
import com.marassu.domain.repository.PetitionRepository
import com.marassu.entity.petition.Petition
import com.marassu.entity.petition.PetitionRequest
import com.marassu.entity.petition.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PetitionRepositoryImpl @Inject constructor(
    private val petitionRemoteDataSource: PetitionRemoteDataSource
): PetitionRepository {
    override suspend fun getPetitionList(
        page: Int,
        size: Int,
        sort: Sort,
        category: String
    ): Flow<ArrayList<Petition>> {
        return flow {
            petitionRemoteDataSource.getPetitionList(page, size, sort, category).body()?.let {
                emit(it)
            }
        }
    }

    override suspend fun postPetition(petitionRequest: PetitionRequest): Flow<Petition> {
        return flow {
            petitionRemoteDataSource.postPetition(petitionRequest).body()?.let {
                emit(it)
            }
        }
    }

    override suspend fun getPetition(petitionId: Long): Flow<Petition> {
        return flow {
            petitionRemoteDataSource.getPetition(petitionId).body()?.let {
                emit(it)
            }
        }
    }

}