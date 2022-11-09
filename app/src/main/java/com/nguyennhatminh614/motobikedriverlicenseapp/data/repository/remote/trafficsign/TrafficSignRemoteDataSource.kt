package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.remote.trafficsign

import com.google.firebase.firestore.FirebaseFirestore
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.TrafficSigns
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.ITrafficSignalDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

class TrafficSignRemoteDataSource(
    private val fireStoreDB : FirebaseFirestore
) : ITrafficSignalDataSource.Remote {

    private val trafficSignsCollections by lazy {
        fireStoreDB.collection(AppConstant.TRAFFIC_SIGN_COLLECTION)
    }

    override fun getAllTrafficSignal(listener: IResponseListener<MutableList<TrafficSigns>>) {
        trafficSignsCollections.get().addOnCompleteListener { tasks ->
            if (tasks.isSuccessful) {
                val listTrafficSign = mutableListOf<TrafficSigns>()

                tasks.result.documents.forEach {
                    it.toObject(TrafficSigns::class.java)?.let { notNullObject ->
                        listTrafficSign.add(notNullObject)
                    }
                }

                listener.onSuccess(listTrafficSign)
            } else {
                listener.onError(tasks.exception)
            }
        }
    }
}
