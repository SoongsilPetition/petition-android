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
class StartViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    application: Application
) : AndroidViewModel(application) {

    sealed class Event {
        object NavigateToHome: Event()
    }

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun checkLogin() {
        if(isLogin()) {
            viewModelScope.launch {
                _eventFlow.emit(Event.NavigateToHome)
            }
        }
    }

    private fun isLogin(): Boolean {
        return sharedPreferences.getString(SharedPreferenceModule.ACCESS_TOKEN, "") != ""
    }
}