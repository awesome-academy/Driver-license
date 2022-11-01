package com.nguyennhatminh614.motobikedriverlicenseapp.utils.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    protected val loading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = loading

    protected val exception = MutableLiveData<Exception?>()
    val hasException: LiveData<Exception?>
        get() = exception

    protected fun launchTask(
        onRequest: suspend CoroutineScope.() -> Unit = {},
    ) = viewModelScope.launch {
        showLoading()
        onRequest(this)
    }

    protected fun showLoading() {
        loading.postValue(true)
    }

    protected fun hideLoading() {
        loading.postValue(false)
    }
}
