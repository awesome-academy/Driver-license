package com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.QuestionOptions

interface IBottomSheetListener {
    fun onNextQuestion(listener: IResponseListener<Int>)
    fun onPreviousQuestion(listener: IResponseListener<Int>)
    fun onClickMoveToPosition(index: Int, data: QuestionOptions)
}
