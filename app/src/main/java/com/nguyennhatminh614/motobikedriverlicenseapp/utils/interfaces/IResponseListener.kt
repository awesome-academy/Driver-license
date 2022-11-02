package com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces

interface IResponseListener<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception?)
}
