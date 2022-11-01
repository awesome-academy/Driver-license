package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.StudyCategory
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

class StudyRepository(
    private val local: IStudyDataSource.Local,
    private val remote: IStudyDataSource.Remote,
) : IStudyDataSource.Local, IStudyDataSource.Remote {

    override suspend fun saveProgress(studyCategoryList: List<StudyCategory>) =
        local.saveProgress(studyCategoryList)

    override suspend fun getAllInfoStudyCategory() = local.getAllInfoStudyCategory()

    override suspend fun getListQuestion(listener: IResponseListener<MutableList<Questions>>) =
        remote.getListQuestion(listener)

}
