package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.StudyCategory
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

interface IStudyDataSource {
    interface Local {
        suspend fun saveProgress(studyCategoryList: List<StudyCategory>)
        suspend fun getAllInfoStudyCategory(): List<StudyCategory>
    }

    interface Remote {
        suspend fun getListQuestion(listener: IResponseListener<MutableList<Questions>>)
    }
}
