package com.nguyennhatminh614.motobikedriverlicenseapp.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.StudyCategory.Companion.STUDY_CATEGORY_TABLE
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = STUDY_CATEGORY_TABLE)
data class StudyCategory(
    @PrimaryKey val iconResourceID: Int,
    val title: String,
    val listQuestions: MutableList<Questions> = mutableListOf(),
    val listQuestionsState: MutableList<QuestionOptions> = mutableListOf(),
    val totalNumberOfQuestions: Int = DEFAULT_VALUE_TOTAL_QUESTION,
    var numbersOfSelectedQuestions: Int = DEFAULT_VALUE_SELECTED_QUESTION,
): Parcelable {
    companion object {
        const val STUDY_CATEGORY_TABLE = "STUDY_CATEGORY"
        const val DEFAULT_VALUE_TOTAL_QUESTION = 1
        const val DEFAULT_VALUE_SELECTED_QUESTION = 0

        fun getDiffUtilCallBack() = object : DiffUtil.ItemCallback<StudyCategory>() {
            override fun areItemsTheSame(
                oldItem: StudyCategory,
                newItem: StudyCategory,
            ) = oldItem.iconResourceID == newItem.iconResourceID

            override fun areContentsTheSame(
                oldItem: StudyCategory,
                newItem: StudyCategory,
            ) = oldItem == newItem
        }
    }
}
