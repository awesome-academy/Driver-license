package com.nguyennhatminh614.motobikedriverlicenseapp.utils

interface IResponseListener<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception?)
}
