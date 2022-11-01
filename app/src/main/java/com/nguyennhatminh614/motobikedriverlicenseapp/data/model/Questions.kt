package com.nguyennhatminh614.motobikedriverlicenseapp.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.PropertyName
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions.Companion.TABLE_QUESTION
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_QUESTION)
class Questions : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id = DEFAULT_ID
    var question = ""
    var option = mutableListOf<String>()
    var isFailedTestWhenWrong = false

    @set:PropertyName("image_url")
    @get:PropertyName("image_url")
    var imageUrl = ""

    var hasImageBanner = false
    var answer = ""
    var explain = ""

    @set:PropertyName("question_type")
    @get:PropertyName("question_type")
    var questionType = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Questions

        if (id != other.id) return false
        if (question != other.question) return false
        if (option != other.option) return false
        if (isFailedTestWhenWrong != other.isFailedTestWhenWrong) return false
        if (imageUrl != other.imageUrl) return false
        if (hasImageBanner != other.hasImageBanner) return false
        if (answer != other.answer) return false
        if (explain != other.explain) return false
        if (questionType != other.questionType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + question.hashCode()
        result = 31 * result + option.hashCode()
        result = 31 * result + isFailedTestWhenWrong.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + hasImageBanner.hashCode()
        result = 31 * result + answer.hashCode()
        result = 31 * result + explain.hashCode()
        result = 31 * result + questionType.hashCode()
        return result
    }

    override fun toString(): String {
        return "Questions(id=$id, question='$question', option=$option, " +
                "isFailedTestWhenWrong=$isFailedTestWhenWrong, imageUrl='$imageUrl', " +
                "hasImageBanner=$hasImageBanner, answer='$answer', explain='$explain', " +
                "question_type='$questionType')"
    }


    companion object {
        const val TABLE_QUESTION = "QUESTIONS"
        const val DEFAULT_ID = 1
        fun getDiffCallBack() = object : DiffUtil.ItemCallback<Questions>() {
            override fun areItemsTheSame(
                oldItem: Questions,
                newItem: Questions,
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Questions,
                newItem: Questions,
            ): Boolean = oldItem == newItem
        }
    }
}
