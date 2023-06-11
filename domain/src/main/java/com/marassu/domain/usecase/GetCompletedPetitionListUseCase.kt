package com.marassu.domain.usecase

import com.marassu.domain.repository.PetitionRepository
import com.marassu.entity.petition.Sort

class GetCompletedPetitionListUseCase(
    private val petitionRepository: PetitionRepository
) {
    fun getCompletedPetitionList(
        page: Int = 1,
        size: Int = 10,
        sort: Sort = Sort.CREATED_AT,
        category: String? = null
    ) = petitionRepository.getCompletedPetitionList(page, size, sort, category)
}