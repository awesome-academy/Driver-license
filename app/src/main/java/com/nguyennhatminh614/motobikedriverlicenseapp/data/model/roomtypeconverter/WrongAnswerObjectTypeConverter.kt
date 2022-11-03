package com.nguyennhatminh614.motobikedriverlicenseapp.data.model.roomtypeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.WrongAnswerObject

class WrongAnswerObjectTypeConverter {
    @TypeConverter
    fun convertJsonToListOptionAnswer(json: String): MutableList<WrongAnswerObject?> {
        val typeToken = object : TypeToken<MutableList<WrongAnswerObject>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun convertListOptionAnswerToJson(listQuestions: MutableList<WrongAnswerObject>): String? =
        Gson().toJson(listQuestions)
}
