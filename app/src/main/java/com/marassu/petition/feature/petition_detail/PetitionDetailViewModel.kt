package com.marassu.petition.feature.petition_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.marassu.domain.usecase.GetConcurListUseCase
import com.marassu.domain.usecase.GetPetitionUseCase
import com.marassu.domain.usecase.PostConcurUseCase
import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.Concur
import com.marassu.entity.petition.Petition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetitionDetailViewModel @Inject constructor(
    application: Application,
    val getPetitionUseCase: GetPetitionUseCase,
    val getConcurListUseCase: GetConcurListUseCase,
    val postConcurUseCase: PostConcurUseCase
): AndroidViewModel(application) {
    private var agreementStatus = AgreementStatus.AGREE
    val updatePetitionFlag: MutableStateFlow<Int> = MutableStateFlow(0)
    val updateConcurFlag: MutableStateFlow<Int> = MutableStateFlow(-1)
    var petition: Petition? = null

    val concurList: List<Concur> = listOf()

    fun updateAgreement(toggle: Int) {
        updateConcurFlag.value = toggle
    }

    fun loadPetition(petitionId: Long) {
        viewModelScope.launch {
            getPetitionUseCase.getPetition(petitionId)
                .collect { item ->
                    petition = item
                    updatePetitionFlag.value = 1
                }
        }
    }

    fun getConcur(): Flow<PagingData<Concur>> {
            return getConcurListUseCase.getConcurList(petitionId = petition?.id ?: -1).apply {
                updateConcurFlag.value = 1
            }
    }
}