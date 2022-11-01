package com.nguyennhatminh614.motobikedriverlicenseapp.data.model.roomtypeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuestionTypeConverter {
    @TypeConverter
    fun convertJsonToListOptionAnswer(json: String): MutableList<String?> {
        val typeToken = object : TypeToken<MutableList<String>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun convertListOptionAnswerToJson(listQuestions: MutableList<String>): String? =
        Gson().toJson(listQuestions)
}
