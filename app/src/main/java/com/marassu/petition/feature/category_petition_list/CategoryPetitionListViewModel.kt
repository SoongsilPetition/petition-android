package com.marassu.petition.feature.category_petition_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.marassu.domain.usecase.GetPetitionListUseCase
import com.marassu.entity.petition.Petition
import com.marassu.entity.petition.Sort
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    private val _sortStateFlow: MutableStateFlow<RefreshState> =
        MutableStateFlow(RefreshState.CreatedAt)
    val sortStateFlow: StateFlow<RefreshState>
        get() = _sortStateFlow

    private val _refreshStateFlow: MutableSharedFlow<Event> = MutableSharedFlow()
    val refreshStateFlow: SharedFlow<Event>
        get() = _refreshStateFlow

    sealed class RefreshState {
        object CreatedAt : RefreshState()
        object AgreeCount : RefreshState()
    }

    sealed class Event {
        object Refresh: Event()
        object Idle: Event()
    }

    fun update() {
        viewModelScope.launch {
            _refreshStateFlow.emit(Event.Idle)
            delay(1000L)
            when (sortStateFlow.value) {
                is RefreshState.CreatedAt -> {
                    _sortStateFlow.emit(RefreshState.AgreeCount)
                    buttonText.emit(AGREE_COUNT)
                }

                else -> {
                    _sortStateFlow.emit(RefreshState.CreatedAt)
                    buttonText.emit(CREATED_AT)
                }
            }
            _refreshStateFlow.emit(Event.Refresh)
        }
    }

    fun getPetition(categoryName: String): Flow<PagingData<Petition>> {
        return if (_sortStateFlow.value is RefreshState.AgreeCount) {
            buttonText.value = AGREE_COUNT
            getPetitionListUseCase.getPetitionList(category = categoryName, sort = Sort.AGREE_COUNT)
        } else {
            buttonText.value = CREATED_AT
            getPetitionListUseCase.getPetitionList(category = categoryName, sort = Sort.CREATED_AT)
        }
    }
}