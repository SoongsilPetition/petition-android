package com.marassu.petition.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _error = MutableLiveData<Exception>()
    val error: LiveData<Exception> get() = _error

    protected fun <T> runScope(req: suspend CoroutineScope.() -> T, res: ((T) -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                req().also { value ->
                    launch(Dispatchers.Main) { res?.invoke(value) }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                _error.postValue(e)
            }
        }
    }

}