package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.TrafficSigns
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

class TrafficRepository(
    private val remote: ITrafficSignalDataSource.Remote,
) : ITrafficSignalDataSource.Remote {
    override fun getAllTrafficSignal(listener: IResponseListener<MutableList<TrafficSigns>>)
        = remote.getAllTrafficSignal(listener)
}
