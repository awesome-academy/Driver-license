package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.exam

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Exam

@Dao
interface ExamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewExam(vararg exam: Exam)

    @Update
    suspend fun updateExam(exam: Exam)

    @Delete
    suspend fun deleteExam(vararg exam: Exam)

    @Query("select * from EXAM")
    suspend fun getAllExam() : MutableList<Exam>

    @Query("select * from EXAM where id=:id")
    suspend fun getDetailExamByID(id: Int) : Exam

}
