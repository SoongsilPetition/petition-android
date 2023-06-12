package com.marassu.petition.feature.info

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    application: Application
) : AndroidViewModel(application) {

    fun logout() {
        sharedPreferences.edit {
            putString(SharedPreferenceModule.ACCESS_TOKEN, "")
        }
    }
}