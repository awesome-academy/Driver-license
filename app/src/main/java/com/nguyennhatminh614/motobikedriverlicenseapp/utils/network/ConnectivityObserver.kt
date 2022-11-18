package com.nguyennhatminh614.motobikedriverlicenseapp.utils.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        AVAILABLE,
        LOST_CONNECTION
    }
}
