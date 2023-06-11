package com.marassu.domain.usecase

import com.marassu.domain.repository.CategoryRepository

class GetCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend fun getCategoryList() = categoryRepository.getCategory()
}