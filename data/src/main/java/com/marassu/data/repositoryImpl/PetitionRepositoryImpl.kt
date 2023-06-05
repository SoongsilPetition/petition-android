package com.marassu.data.repositoryImpl

import com.marassu.data.source.PetitionRemoteDataSource
import com.marassu.data.util.CommonAPILogic
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
        return CommonAPILogic.checkError(petitionRemoteDataSource.getPetitionList(page, size, sort, category))
    }

    override suspend fun postPetition(petitionRequest: PetitionRequest): Flow<Petition> {
        return CommonAPILogic.checkError(petitionRemoteDataSource.postPetition(petitionRequest))
    }

    override suspend fun getPetition(petitionId: Long): Flow<Petition> {
        return CommonAPILogic.checkError(petitionRemoteDataSource.getPetition(petitionId))
    }

}