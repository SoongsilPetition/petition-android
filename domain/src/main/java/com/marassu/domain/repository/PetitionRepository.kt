package com.marassu.domain.repository

import androidx.paging.PagingData
import com.marassu.entity.petition.Petition
import com.marassu.entity.petition.PetitionRequest
import com.marassu.entity.petition.Sort
import kotlinx.coroutines.flow.Flow

interface PetitionRepository {
    fun getPetitionList(
        page: Int = 1,
        size: Int = 10,
        sort: Sort = Sort.CREATED_AT,
        category: String? = null
    ): Flow<PagingData<Petition>>

    fun getCompletedPetitionList(
        page: Int = 1,
        size: Int = 10,
        sort: Sort = Sort.CREATED_AT,
        category: String? = null
    ): Flow<PagingData<Petition>>

    suspend fun postPetition(petitionRequest: PetitionRequest): Flow<Petition>

    suspend fun getPetition(petitionId: Long): Flow<Petition>
}