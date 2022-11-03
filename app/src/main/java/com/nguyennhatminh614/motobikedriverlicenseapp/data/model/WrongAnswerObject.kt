package com.nguyennhatminh614.motobikedriverlicenseapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.WrongAnswerObject.Companion.WRONG_ANSWER_TABLE
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = WRONG_ANSWER_TABLE)
data class WrongAnswerObject(
    @PrimaryKey val questionsID: Int,
    val lastWrongTime: Long,
) : Parcelable {
    companion object {
        const val WRONG_ANSWER_TABLE = "WRONG_ANSWER_TABLE"
    }
}
