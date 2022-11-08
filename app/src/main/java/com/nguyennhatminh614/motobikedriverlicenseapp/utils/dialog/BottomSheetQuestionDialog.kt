package com.nguyennhatminh614.motobikedriverlicenseapp.utils.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.QuestionOptions
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.DialogBottomSheetQuestionsListBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.screen.appadapter.BottomSheetQuestionListAdapter
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.CountDownInstance
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IBottomSheetListener
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BottomSheetQuestionDialog {
    private var bottomSheetDialog: BottomSheetDialog? = null
    private var countDownExamCurrentJob: Job? = null
    private lateinit var dialogBinding: DialogBottomSheetQuestionsListBinding
    val bottomSheetAdapter by lazy { BottomSheetQuestionListAdapter() }

    fun initDialog(
        context: Context,
        listQuestionOptions: MutableList<QuestionOptions>?,
        currentSelectedPos: Int,
    ) {
        dialogBinding =
            DialogBottomSheetQuestionsListBinding.inflate(LayoutInflater.from(context))

        bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog?.apply {
            setContentView(dialogBinding.root)
            window?.attributes = WindowManager.LayoutParams().apply {
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.MATCH_PARENT
            }
            dialogBinding.recyclerViewQuestionIconList.adapter = bottomSheetAdapter
            dialogBinding.textCurrentQuestions.text =
                "Câu ${currentSelectedPos + 1}/${listQuestionOptions?.size}"
            bottomSheetAdapter.apply {
                submitList(listQuestionOptions)
                setSingleSelection(currentSelectedPos)
            }
        }
    }

    fun examDialogMode(isExam: Boolean, isRunning: Boolean) {
        if (isExam && isRunning) {
            setLayoutForTextInDetailExamScreen(dialogBinding)

            countDownExamCurrentJob = bottomSheetDialog?.lifecycleScope?.launch {
                while (CountDownInstance.CurrentTimeStamp != END_EXAM_TIME_STAMP) {
                    dialogBinding.textCurrentTime.text = CountDownInstance.CurrentTime
                    delay(INTERVAL_REQUEST_TIME_STRING)
                }
            }

            bottomSheetDialog?.setOnDismissListener {
                countDownExamCurrentJob?.cancel()
            }
        } else {
            dialogBinding.textCurrentTime.isVisible = false
            (dialogBinding.textCurrentQuestions.layoutParams as?
                    ConstraintLayout.LayoutParams)?.horizontalBias =
                SET_HORIZONTAL_BIAS_WHEN_EXAM_DONE
            dialogBinding.root.requestLayout()
        }
    }

    fun setDialogEvent(bottomSheetListener: IBottomSheetListener) {
        val positionListener = object : IResponseListener<Int> {
            override fun onSuccess(data: Int) {
                dialogBinding.textCurrentQuestions.text =
                    "Câu ${data + 1}/${bottomSheetAdapter.currentList.size}"
                bottomSheetAdapter.setSingleSelection(data)
            }

            override fun onError(exception: Exception?) {
                //Not-op
            }
        }

        dialogBinding.buttonNextQuestion.setOnClickListener {
            bottomSheetListener.onNextQuestion(positionListener)
        }

        dialogBinding.buttonPreviousQuestion.setOnClickListener {
            bottomSheetListener.onPreviousQuestion(positionListener)
        }

        bottomSheetAdapter.registerOnClickItemPositionEvent { position, data ->
            bottomSheetAdapter.setSingleSelection(position)
            dialogBinding.textCurrentQuestions.text =
                "Câu ${position + 1}/${bottomSheetAdapter.currentList.size}"
            bottomSheetListener.onClickMoveToPosition(position, data)
        }
    }

    fun updateDataAdapter(listData: MutableList<QuestionOptions>) {
        bottomSheetAdapter.submitList(listData)
    }

    fun showDialog() = bottomSheetDialog?.show()

    private fun setLayoutForTextInDetailExamScreen(
        dialogBinding: DialogBottomSheetQuestionsListBinding,
    ) {
        (dialogBinding.textCurrentQuestions.layoutParams as ConstraintLayout.LayoutParams)
            .horizontalBias = SET_HORIZONTAL_BIAS_TEXT_CURRENT_QUESTION_IN_EXAM_SCREEN
        dialogBinding.textCurrentTime.isVisible = true
        dialogBinding.root.requestLayout()
    }

    companion object {
        private const val END_EXAM_TIME_STAMP = 0L
        private const val INTERVAL_REQUEST_TIME_STRING = 950L
        private const val SET_HORIZONTAL_BIAS_WHEN_EXAM_DONE = 0.5F
        private const val SET_HORIZONTAL_BIAS_TEXT_CURRENT_QUESTION_IN_EXAM_SCREEN = 0.35F
    }
}
