package com.nguyennhatminh614.motobikedriverlicenseapp.data.model.roomtypeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.QuestionOptions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions

class QuestionOptionTypeConverter {
    @TypeConverter
    fun convertJsonToFullListOptionAnswer(json: String): MutableList<QuestionOptions>? {
        val typeToken = object : TypeToken<MutableList<QuestionOptions>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun convertFullListOptionAnswerToJson(listQuestions: MutableList<QuestionOptions>): String? =
        Gson().toJson(listQuestions)

}
