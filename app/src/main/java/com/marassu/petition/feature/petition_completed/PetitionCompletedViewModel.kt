package com.marassu.petition.feature.petition_completed

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.marassu.domain.usecase.GetConcurListUseCase
import com.marassu.domain.usecase.GetPetitionAnswerUseCase
import com.marassu.domain.usecase.PostConcurUseCase
import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.Concur
import com.marassu.entity.concur.ConcurRequest
import com.marassu.entity.petition.Petition
import com.marassu.entity.petition_answer.PetitionAnswer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PetitionCompletedViewModel @Inject constructor(
    application: Application,
    private val getConcurListUseCase: GetConcurListUseCase,
    private val postConcurUseCase: PostConcurUseCase,
    private val getPetitionAnswerUseCase: GetPetitionAnswerUseCase
) : AndroidViewModel(application) {
    val updatePetitionFlag: MutableStateFlow<Int> = MutableStateFlow(0)
    var petitionAnswer: PetitionAnswer? = null
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
            getPetitionAnswerUseCase.getPetitionAnswer(petitionId)
                .catch {
                    Timber.e("error ${it.message}")
                }
                .collect {
                    petitionAnswer = it
                    updatePetitionFlag.value = 1
                }
        }
    }
}