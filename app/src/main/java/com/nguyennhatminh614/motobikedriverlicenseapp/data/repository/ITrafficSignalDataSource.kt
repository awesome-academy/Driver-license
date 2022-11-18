package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.TrafficSigns
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

interface ITrafficSignalDataSource {
    interface Remote {
        fun getAllTrafficSignal(listener: IResponseListener<MutableList<TrafficSigns>>)
    }
}
