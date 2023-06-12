package com.marassu.petition.feature.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.PagingData
import com.marassu.domain.usecase.GetPetitionListUseCase
import com.marassu.entity.petition.Petition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPetitionListUseCase: GetPetitionListUseCase,
    application: Application
) : AndroidViewModel(application) {
    fun getPetition(): Flow<PagingData<Petition>> = getPetitionListUseCase.getPetitionList()
}