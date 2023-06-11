package com.marassu.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marassu.data.service.PetitionService
import com.marassu.entity.petition.Petition
import com.marassu.entity.petition.PetitionRequest
import com.marassu.entity.petition.Sort
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit

const val NETWORK_PAGE_SIZE = 25

class PetitionRemoteDataSource constructor(
    private val retrofitPair: Pair<Retrofit, Retrofit>
) {

    private val publicPetitionService = retrofitPair.first.create(PetitionService::class.java)
    private val tokenPetitionService = retrofitPair.second.create(PetitionService::class.java)

    fun getPetitionList(
        page: Int = 1,
        size: Int = 10,
        sort: Sort = Sort.CREATED_AT,
        category: String? = null
    ): Flow<PagingData<Petition>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PetitionPageSource(service = publicPetitionService, sort, category)
            }
        ).flow
    }

    suspend fun postPetition(petitionRequest: PetitionRequest) = tokenPetitionService.postPetition(petitionRequest)

    suspend fun getPetition(petitionId: Long) = publicPetitionService.getPetition(petitionId)
}