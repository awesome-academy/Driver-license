package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Exam
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

class ExamRepository(
    private val local: IExamDataSource.Local,
    private val remote: IExamDataSource.Remote,
) : IExamDataSource.Local, IExamDataSource.Remote {

    override suspend fun getListQuestion(listener: IResponseListener<MutableList<Questions>>) =
        remote.getListQuestion(listener)

    override suspend fun insertNewExam(exam: Exam) = local.insertNewExam(exam)

    override suspend fun updateExam(exam: Exam) = local.updateExam(exam)

    override suspend fun deleteExam(exam: Exam) = local.deleteExam(exam)

    override suspend fun getAllExam(): MutableList<Exam> = local.getAllExam()

    override suspend fun getDetailExamByID(id: Int): Exam = local.getDetailExamByID(id)

}
