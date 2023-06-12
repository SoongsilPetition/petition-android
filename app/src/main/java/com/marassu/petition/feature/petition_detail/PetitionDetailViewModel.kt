package com.marassu.petition.feature.petition_detail

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.marassu.domain.usecase.GetConcurListUseCase
import com.marassu.domain.usecase.GetPetitionUseCase
import com.marassu.domain.usecase.PostConcurUseCase
import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.Concur
import com.marassu.entity.concur.ConcurRequest
import com.marassu.entity.petition.Petition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PetitionDetailViewModel @Inject constructor(
    application: Application,
    private val getPetitionUseCase: GetPetitionUseCase,
    private val getConcurListUseCase: GetConcurListUseCase,
    private val postConcurUseCase: PostConcurUseCase
) : AndroidViewModel(application) {
    val updatePetitionFlag: MutableStateFlow<Int> = MutableStateFlow(0)
    val updateConcurFlag: MutableStateFlow<Int> = MutableStateFlow(-1)
    val concurList = mutableStateListOf<Concur>()
    var petition: Petition? = null
    var agreementStatus: AgreementStatus = AgreementStatus.AGREE

    fun updateAgreement(toggle: Int) {
        if (toggle == 0) {
            agreementStatus = AgreementStatus.AGREE
        } else {
            agreementStatus = AgreementStatus.DISAGREE
        }
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

    fun postConcur(text: String) {
        viewModelScope.launch {
            postConcurUseCase.postConcur(
                ConcurRequest(
                    content = text,
                    petitionId = petition?.id ?: -1,
                    agreementStatus = agreementStatus
                )
            ).collect {concur ->
                concurList.add(concur)
            }
        }
    }

    fun getConcur(){
        viewModelScope.launch {
            getConcurListUseCase.getConcurList(petition?.id ?: -1)
                .catch {
                    Timber.e("error : ${it.message}")
                }
                .collect {
                    concurList.addAll(it)
                }
        }
    }
}