package com.nguyennhatminh614.motobikedriverlicenseapp.utils.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.QuestionOptions
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.DialogBottomSheetQuestionsListBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.screen.questions.BottomSheetQuestionListAdapter
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IBottomSheetListener
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

class BottomSheetQuestionDialog {
    private var bottomSheetDialog: BottomSheetDialog? = null
    val bottomSheetAdapter by lazy { BottomSheetQuestionListAdapter() }

    fun showDialog(
        context: Context,
        listQuestionOptions: MutableList<QuestionOptions>?,
        currentSelectedPos: Int,
        bottomSheetListener: IBottomSheetListener,
        totalNumberQuestion: Int
    ) {
        val dialogBinding =
            DialogBottomSheetQuestionsListBinding.inflate(LayoutInflater.from(context))
        val positionListener = object : IResponseListener<Int> {
            override fun onSuccess(data: Int) {
                dialogBinding.textCurrentQuestions.text = "Câu ${data + 1}/$totalNumberQuestion"
                bottomSheetAdapter.setSingleSelection(data)
            }

            override fun onError(exception: Exception?) {
                //Not-op
            }
        }

        bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog?.apply {
            setContentView(dialogBinding.root)
            window?.attributes = WindowManager.LayoutParams().apply {
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.MATCH_PARENT
            }
            dialogBinding.recyclerViewQuestionIconList.adapter = bottomSheetAdapter

            dialogBinding.buttonNextQuestion.setOnClickListener {
                bottomSheetListener.onNextQuestion(positionListener)
            }

            dialogBinding.buttonPreviousQuestion.setOnClickListener {
                bottomSheetListener.onPreviousQuestion(positionListener)
            }

            dialogBinding.textCurrentQuestions.text = "Câu ${currentSelectedPos + 1}/$totalNumberQuestion"

            bottomSheetAdapter.apply {
                submitList(listQuestionOptions)
                setSingleSelection(currentSelectedPos)
                registerOnClickItemPositionEvent { position, data ->
                    bottomSheetAdapter.setSingleSelection(position)
                    dialogBinding.textCurrentQuestions.text = "Câu ${position + 1}/$totalNumberQuestion"
                    bottomSheetListener.onClickMoveToPosition(position, data)
                }
            }

            bottomSheetDialog?.show()
        }
    }

    fun updateDataAdapter(listData: MutableList<QuestionOptions>) {
        bottomSheetAdapter.submitList(listData)
    }

    fun hideDialog() = bottomSheetDialog?.hide()
}
