package com.marassu.petition.feature.write

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.marassu.data.util.HttpException
import com.marassu.domain.usecase.PostPetitionUseCase
import com.marassu.entity.petition.PetitionCategory
import com.marassu.entity.petition.PetitionRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val postPetitionUseCase: PostPetitionUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _content = MutableStateFlow("")
    val content = _content.asStateFlow()

    private val _category = MutableStateFlow("")
    val category = _category.asStateFlow()

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setContent(content: String) {
        _content.value = content
    }

    fun setCategory(category: String) {
        _category.value = category
    }

    fun write() {
        viewModelScope.launch {
            postPetitionUseCase.postPetition(
                PetitionRequest(
                    title.value,
                    content.value,
                    listOf(PetitionCategory(category.value))
                )
            )
                .catch {
                    if (it is HttpException) {
                        Timber.e("code : ${it.code}, message: ${it.message}")
                    }
                }
                .collect {
                    Timber.d("success")
                    Timber.d("token : $it")
                }
        }
    }

}