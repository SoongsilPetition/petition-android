package com.marassu.data.source

import com.marassu.data.service.PetitionService
import com.marassu.entity.petition.PetitionRequest
import com.marassu.entity.petition.Sort
import retrofit2.Retrofit

class PetitionRemoteDataSource constructor(
    private val retrofit: Retrofit
) {
    private val petitionService = retrofit.create(PetitionService::class.java)

    suspend fun getPetitionList(
        page: Int = 1,
        size: Int = 10,
        sort: Sort = Sort.CREATED_AT,
        category: String
    ) = petitionService.getPetitionList(page, size, sort, category)

    suspend fun postPetition(petitionRequest: PetitionRequest) = petitionService.postPetition(petitionRequest)

    suspend fun getPetition(petitionId: Long) = petitionService.getPetition(petitionId)
}