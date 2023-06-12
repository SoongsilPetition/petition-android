package com.marassu.petition.feature.category_petition_list

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.marassu.domain.usecase.GetPetitionListUseCase
import com.marassu.entity.petition.Petition
import com.marassu.entity.petition.Sort
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoryPetitionListViewModel @Inject constructor(
    application: Application,
    private val getPetitionListUseCase: GetPetitionListUseCase
) : AndroidViewModel(application) {
    companion object {
        private val CREATED_AT = "최신 정렬 순"
        private val AGREE_COUNT = "동의 많은 순"
    }
    val buttonText = MutableStateFlow(CREATED_AT)

    val refreshEvent = MutableStateFlow(0)

    fun update() {
        refreshEvent.value = if(refreshEvent.value == 0) 1 else 0
    }
    fun getPetition(categoryName: String): Flow<PagingData<Petition>> =
        getPetitionListUseCase.getPetitionList(category = categoryName)

    fun changeSetting(categoryName: String): Flow<PagingData<Petition>> {
        return if(buttonText.value == CREATED_AT) {
            buttonText.value = AGREE_COUNT
            getPetitionListUseCase.getPetitionList(category = categoryName, sort = Sort.AGREE_COUNT)
        } else {
            buttonText.value = CREATED_AT
            getPetitionListUseCase.getPetitionList(category = categoryName, sort = Sort.CREATED_AT)
        }
    }
}