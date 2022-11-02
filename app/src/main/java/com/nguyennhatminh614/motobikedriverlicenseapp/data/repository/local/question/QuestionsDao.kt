package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.question

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions

@Dao
interface QuestionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewQuestions(vararg questions: Questions)

    @Delete
    suspend fun deleteQuestions(vararg questions: Questions)

    @Query("select * from QUESTIONS")
    suspend fun getAllQuestions(): MutableList<Questions>

    @Query("select * from QUESTIONS where id=:id")
    suspend fun getDetailQuestionsByID(id: Int): Questions

}
