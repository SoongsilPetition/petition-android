package com.marassu.data.repositoryImpl

import com.marassu.data.source.CategoryRemoteDataSource
import com.marassu.data.util.CommonAPILogic
import com.marassu.domain.repository.CategoryRepository
import com.marassu.entity.petition.PetitionCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource
): CategoryRepository {
    override suspend fun getCategory(): Flow<List<PetitionCategory>> {
        return CommonAPILogic.checkError(categoryRemoteDataSource.getCategory())
    }
}