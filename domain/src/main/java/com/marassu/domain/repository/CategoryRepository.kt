package com.marassu.domain.repository

import com.marassu.entity.petition.PetitionCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategory(): Flow<List<PetitionCategory>>
}