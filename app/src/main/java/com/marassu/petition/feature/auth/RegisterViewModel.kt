package com.marassu.petition.feature.auth

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.runtime.collectAsState
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.marassu.data.util.HttpException
import com.marassu.domain.usecase.PostUserLoginUseCase
import com.marassu.domain.usecase.PostUserRegisterUseCase
import com.marassu.entity.user.UserLoginRequest
import com.marassu.entity.user.UserRegisterRequest
import com.marassu.petition.di.SharedPreferenceModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val postUserRegisterUseCase: PostUserRegisterUseCase,
    private val sharedPreferences: SharedPreferences,
    application: Application
) : AndroidViewModel(application) {

    sealed class Event {
        object Failure: Event()
        object NavigateToBack: Event()
    }

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun register() {
        viewModelScope.launch {
            postUserRegisterUseCase.postUserRegister(
                UserRegisterRequest(
                    name.value,
                    "${email.value}@soongsil.ac.kr",
                    password.value
                )
            )
                .catch {
                    if (it is HttpException) {
                        Timber.e("code : ${it.code}, message: ${it.message}")
                    }
                    _eventFlow.emit(Event.Failure)
                }
                .collect {
                    Timber.d("success")
                    Timber.d("token : $it")
                    _eventFlow.emit(Event.NavigateToBack)
                }
        }
    }

}