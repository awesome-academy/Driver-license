package com.nguyennhatminh614.motobikedriverlicenseapp.data.model.roomtypeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions

class ExamTypeConverter {
    @TypeConverter
    fun convertJsonToListQuestion(json: String): MutableList<Questions?> {
        val typeToken = object : TypeToken<MutableList<Questions>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun convertListQuestionToJson(listQuestions: MutableList<Questions>): String? =
        Gson().toJson(listQuestions)
}
