package com.marassu.data.service

import com.marassu.entity.petition.PetitionCategory
import retrofit2.Response
import retrofit2.http.GET

interface CategoryService {
    @GET("/category")
    suspend fun getCategory(): Response<List<PetitionCategory>>
}