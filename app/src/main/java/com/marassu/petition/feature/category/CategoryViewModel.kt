package com.marassu.petition.feature.category

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.marassu.domain.usecase.GetCategoryUseCase
import com.marassu.entity.petition.PetitionCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    application: Application,
    private val getCategoryUseCase: GetCategoryUseCase
) : AndroidViewModel(application) {
    val categoryList = mutableStateListOf<PetitionCategory>()

    fun getPetitionCategory() {
        viewModelScope.launch {
            getCategoryUseCase.getCategoryList()
                .catch {
                    Timber.e(it.message)
                }
                .collect{
                    categoryList.addAll(it)
                }
        }
    }
}