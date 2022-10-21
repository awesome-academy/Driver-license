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

    private var countLoading = 0

    protected fun launchTask(
        isShowLoading: Boolean = true,
        onRequest: suspend CoroutineScope.() -> Unit = {},
    ) = viewModelScope.launch {
        showLoading(isShowLoading)
        onRequest(this)
    }

    private fun showLoading(isShowLoading: Boolean) {
        if (!isShowLoading) return
        countLoading++
        if (loading.value != true) loading.value = true
    }

    protected fun hideLoading(isShowLoading: Boolean) {
        if (!isShowLoading) return
        countLoading--
        if (countLoading <= 0) {
            countLoading = 0
            loading.value = false
        }
    }
}
