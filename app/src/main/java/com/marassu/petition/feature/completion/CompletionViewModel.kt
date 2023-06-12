package com.marassu.petition.feature.completion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.PagingData
import com.marassu.domain.usecase.GetCompletedPetitionListUseCase
import com.marassu.entity.petition.Petition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CompletionViewModel @Inject constructor(
    application: Application,
    private val getCompletedPetitionListUseCase: GetCompletedPetitionListUseCase
) : AndroidViewModel(application) {
    fun getPetition(): Flow<PagingData<Petition>> = getCompletedPetitionListUseCase.getCompletedPetitionList()
}