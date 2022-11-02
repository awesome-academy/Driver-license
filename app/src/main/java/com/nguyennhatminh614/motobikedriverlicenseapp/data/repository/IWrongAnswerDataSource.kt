package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.WrongAnswerObject
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

interface IWrongAnswerDataSource {
    interface Local {
        suspend fun getAllWrongAnswerQuestion(): MutableList<WrongAnswerObject>
        suspend fun insertNewWrongAnswerQuestion(wrongAnswerObject: WrongAnswerObject)
        suspend fun updateWrongAnswerQuestion(wrongAnswerObject: WrongAnswerObject)
        suspend fun checkWrongAnswerQuestionExists(id: Int): Boolean
    }

    interface Remote {
        suspend fun getAllListQuestion(listener: IResponseListener<MutableList<Questions>>)
    }
}
