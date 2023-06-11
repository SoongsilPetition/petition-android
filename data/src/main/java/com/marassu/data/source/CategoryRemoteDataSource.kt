package com.marassu.data.source

import com.marassu.data.service.CategoryService
import retrofit2.Retrofit

class CategoryRemoteDataSource(
    val retrofitPair: Pair<Retrofit, Retrofit>
){
    private val categoryService: CategoryService = retrofitPair.first.create(CategoryService::class.java)

    suspend fun getCategory() = categoryService.getCategory()
}