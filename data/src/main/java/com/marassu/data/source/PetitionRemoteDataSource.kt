package com.marassu.data.source

import com.marassu.data.service.PetitionService
import com.marassu.entity.petition.PetitionRequest
import com.marassu.entity.petition.Sort
import retrofit2.Retrofit

class PetitionRemoteDataSource constructor(
    private val retrofitPair: Pair<Retrofit, Retrofit>
) {
    private val publicPetitionService = retrofitPair.first.create(PetitionService::class.java)
    private val tokenPetitionService = retrofitPair.second.create(PetitionService::class.java)

    suspend fun getPetitionList(
        page: Int = 1,
        size: Int = 10,
        sort: Sort = Sort.CREATED_AT,
        category: String
    ) = publicPetitionService.getPetitionList(page, size, sort, category)

    suspend fun postPetition(petitionRequest: PetitionRequest) = tokenPetitionService.postPetition(petitionRequest)

    suspend fun getPetition(petitionId: Long) = publicPetitionService.getPetition(petitionId)
}