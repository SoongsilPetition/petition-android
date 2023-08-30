package com.marassu.petition.feature.auth

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.runtime.collectAsState
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.marassu.data.util.HttpException
import com.marassu.domain.usecase.PostUserLoginUseCase
import com.marassu.entity.user.UserLoginRequest
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
class LoginViewModel @Inject constructor(
    private val postUserLoginUseCase: PostUserLoginUseCase,
    private val sharedPreferences: SharedPreferences,
    application: Application
) : AndroidViewModel(application) {
    sealed class Event {
        object Failure: Event()
        object NavigateToHome : Event()
    }

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun login() {
        viewModelScope.launch {
            postUserLoginUseCase.postUserLogin(
                UserLoginRequest(
                    "${email.value}@soongsil.ac.kr",
                    password.value
                )
            )
                .catch {
                    if (it is HttpException) {
                        _eventFlow.emit(Event.Failure)
                        Timber.e("code : ${it.code}, message: ${it.message}")
                    }
                }
                .collect {
                    sharedPreferences.edit {
                        putString(SharedPreferenceModule.ACCESS_TOKEN, it.token)
                    }
                    Timber.d("success")
                    Timber.d("token : ${it.token}")
                    _eventFlow.emit(Event.NavigateToHome)
                }
        }
    }

}