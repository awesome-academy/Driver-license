package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.WrongAnswerObject
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

class WrongAnswerRepository(
    private val local: IWrongAnswerDataSource.Local,
    private val remote: IWrongAnswerDataSource.Remote,
) : IWrongAnswerDataSource.Local, IWrongAnswerDataSource.Remote {

    override suspend fun getAllWrongAnswerQuestion(): MutableList<WrongAnswerObject> =
        local.getAllWrongAnswerQuestion()

    override suspend fun insertNewWrongAnswerQuestion(wrongAnswerObject: WrongAnswerObject) =
        local.insertNewWrongAnswerQuestion(wrongAnswerObject)

    override suspend fun updateWrongAnswerQuestion(wrongAnswerObject: WrongAnswerObject) =
        local.updateWrongAnswerQuestion(wrongAnswerObject)

    override suspend fun checkWrongAnswerQuestionExists(id: Int): Boolean =
        local.checkWrongAnswerQuestionExists(id)

    override suspend fun getAllListQuestion(listener: IResponseListener<MutableList<Questions>>) =
        remote.getAllListQuestion(listener)

}
