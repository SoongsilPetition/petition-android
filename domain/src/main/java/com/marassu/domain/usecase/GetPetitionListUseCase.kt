package com.marassu.domain.usecase

import com.marassu.domain.repository.PetitionRepository
import com.marassu.entity.petition.Sort

class GetPetitionListUseCase(private val petitionRepository: PetitionRepository) {
    fun getPetitionList(
        page: Int = 1,
        size: Int = 10,
        sort: Sort = Sort.CREATED_AT,
        category: String? = null
    ) = petitionRepository.getPetitionList(page, size, sort, category)
}