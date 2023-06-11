package com.marassu.data.repositoryImpl

import androidx.paging.PagingData
import com.marassu.data.source.PetitionRemoteDataSource
import com.marassu.data.util.CommonAPILogic
import com.marassu.domain.repository.PetitionRepository
import com.marassu.entity.petition.Petition
import com.marassu.entity.petition.PetitionRequest
import com.marassu.entity.petition.Sort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetitionRepositoryImpl @Inject constructor(
    private val petitionRemoteDataSource: PetitionRemoteDataSource
): PetitionRepository {
    override fun getPetitionList(
        page: Int,
        size: Int,
        sort: Sort,
        category: String?
    ): Flow<PagingData<Petition>> {
        return petitionRemoteDataSource.getPetitionList(page, size, sort, category)
    }

    override fun getCompletedPetitionList(
        page: Int,
        size: Int,
        sort: Sort,
        category: String?
    ): Flow<PagingData<Petition>> {
        return petitionRemoteDataSource.getCompletedPetitionList(page, size, sort, category)
    }

    override suspend fun postPetition(petitionRequest: PetitionRequest): Flow<Petition> {
        return CommonAPILogic.checkError(petitionRemoteDataSource.postPetition(petitionRequest))
    }

    override suspend fun getPetition(petitionId: Long): Flow<Petition> {
        return CommonAPILogic.checkError(petitionRemoteDataSource.getPetition(petitionId))
    }

}