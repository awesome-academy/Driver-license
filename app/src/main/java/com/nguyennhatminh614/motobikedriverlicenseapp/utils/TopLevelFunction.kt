package com.nguyennhatminh614.motobikedriverlicenseapp.utils

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.QuestionOptions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant

fun generateEmptyQuestionStateList(numbersOfQuestion: Int): MutableList<QuestionOptions> {
    return MutableList(numbersOfQuestion) {
        QuestionOptions(AppConstant.NONE_POSITION, AppConstant.EMPTY_DATA)
    }
}
