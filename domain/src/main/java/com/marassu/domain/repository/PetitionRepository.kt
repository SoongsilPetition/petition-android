package com.marassu.domain.repository

import com.marassu.entity.petition.Petition
import com.marassu.entity.petition.PetitionRequest
import com.marassu.entity.petition.Sort
import kotlinx.coroutines.flow.Flow

interface PetitionRepository {
    suspend fun getPetitionList(
        page: Int = 1,
        size: Int = 10,
        sort: Sort = Sort.CREATED_AT,
        category: String
    ): Flow<ArrayList<Petition>>

    suspend fun postPetition(petitionRequest: PetitionRequest): Flow<Petition>

    suspend fun getPetition(petitionId: Long): Flow<Petition>
}