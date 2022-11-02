package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.study

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.StudyCategory
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.IStudyDataSource

class StudyLocalDataSource(
    private val studyDao: StudyDao,
) : IStudyDataSource.Local {

    override suspend fun saveProgress(studyCategoryList: List<StudyCategory>) =
        studyDao.saveNewStudyCategory(studyCategoryList)

    override suspend fun getAllInfoStudyCategory(): List<StudyCategory> =
        studyDao.getAllInfoStudyCategory()

}

