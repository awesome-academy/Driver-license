package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.wronganswer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.WrongAnswerObject

@Dao
interface WrongAnswerDao {

    @Query("select * from wrong_answer_table order by lastWrongTime desc")
    suspend fun getAllWrongAnswerQuestion(): MutableList<WrongAnswerObject>

    @Query("select count(*) from wrong_answer_table where questionsID = :id")
    suspend fun checkWrongAnswerQuestionExists(id: Int) : Int

    @Insert
    suspend fun insertNewWrongAnswerQuestion(vararg wrongAnswerObject: WrongAnswerObject)

    @Update
    suspend fun updateWrongAnswerQuestion(vararg wrongAnswerObject: WrongAnswerObject)
}
