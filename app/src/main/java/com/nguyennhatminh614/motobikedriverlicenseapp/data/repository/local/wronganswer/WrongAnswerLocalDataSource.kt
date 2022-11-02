package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.wronganswer

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.WrongAnswerObject
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.IWrongAnswerDataSource

class WrongAnswerLocalDataSource(
    private val wrongAnswerDao: WrongAnswerDao,
) : IWrongAnswerDataSource.Local {

    override suspend fun getAllWrongAnswerQuestion(): MutableList<WrongAnswerObject>
        = wrongAnswerDao.getAllWrongAnswerQuestion()

    override suspend fun insertNewWrongAnswerQuestion(wrongAnswerObject: WrongAnswerObject)
        = wrongAnswerDao.insertNewWrongAnswerQuestion(wrongAnswerObject)

    override suspend fun updateWrongAnswerQuestion(wrongAnswerObject: WrongAnswerObject)
        = wrongAnswerDao.updateWrongAnswerQuestion(wrongAnswerObject)

    override suspend fun checkWrongAnswerQuestionExists(id: Int): Boolean =
        wrongAnswerDao.checkWrongAnswerQuestionExists(id) > NOT_FOUNDED_RECORD

    companion object {
        private const val NOT_FOUNDED_RECORD = 0
    }
}
