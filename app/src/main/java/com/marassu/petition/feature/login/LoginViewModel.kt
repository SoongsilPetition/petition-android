package com.marassu.petition.feature.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.marassu.data.source.UserRemoteDataSource
import com.marassu.data.util.HttpException
import com.marassu.domain.usecase.PostUserLoginUseCase
import com.marassu.entity.user.UserLoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postUserLoginUseCase: PostUserLoginUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun login() {
        viewModelScope.launch {
            postUserLoginUseCase.postUserLogin(UserLoginRequest(_email.value, _password.value))
                .catch {
                    if(it is HttpException) {
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