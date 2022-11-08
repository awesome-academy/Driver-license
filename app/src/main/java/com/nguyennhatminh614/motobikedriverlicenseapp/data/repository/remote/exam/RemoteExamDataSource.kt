package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.remote.exam

import com.google.firebase.firestore.FirebaseFirestore
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.IExamDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

class RemoteExamDataSource(
    private val fireStoreDB: FirebaseFirestore,
) : IExamDataSource.Remote {

    private val questionCollections by lazy {
        fireStoreDB.collection(AppConstant.QUESTION_COLLECTION)
    }

    override suspend fun getListQuestion(listener: IResponseListener<MutableList<Questions>>) {
        questionCollections.get().addOnCompleteListener { tasks ->
            if (tasks.isSuccessful) {
                val listQuestions = mutableListOf<Questions>()

                tasks.result.documents.forEach {
                    it.toObject(Questions::class.java)?.let { notNullObject ->
                        listQuestions.add(notNullObject)
                    }
                }

                listener.onSuccess(listQuestions)
            } else {
                listener.onError(tasks.exception)
            }
        }
    }
}
