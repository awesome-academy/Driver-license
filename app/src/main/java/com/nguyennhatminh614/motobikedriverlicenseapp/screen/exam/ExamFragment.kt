package com.nguyennhatminh614.motobikedriverlicenseapp.screen.exam

import android.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.nguyennhatminh614.motobikedriverlicenseapp.R
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Exam
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.ExamState
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.FragmentExamBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseFragment
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant.FIRST_INDEX
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.extensions.showToast
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.generateEmptyQuestionStateList
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ExamFragment : BaseFragment<FragmentExamBinding>(FragmentExamBinding::inflate) {

    override val viewModel by sharedViewModel<ExamViewModel>()

    private val examAdapter by lazy { ExamAdapter() }

    private val listTitle = listOf(
        AppConstant.MUST_NOT_WRONG_ANSWER,
        AppConstant.CONCEPTS_AND_RULES,
        AppConstant.TRAFFIC_SIGNAL,
        AppConstant.TRAFFIC_SITUATION_BY_PICTURE,
    )

    private val listNumbersOfQuestion = listOf(
        ExamViewModel.NUMBERS_OF_MUST_NOT_WRONG_ANSWER,
        ExamViewModel.NUMBERS_OF_CONCEPT_AND_RULES,
        ExamViewModel.NUMBERS_OF_TRAFFIC_SIGNAL,
        ExamViewModel.NUMBERS_OF_SITUATION_BY_PICTURE,
    )

    override fun initData() {
        viewBinding.recyclerViewExam.adapter = examAdapter
    }

    override fun handleEvent() {
        examAdapter.registerOnClickExamButtonEvent {
            viewModel.setCurrentExam(it)
            if (viewModel.listExam.value?.get(it)?.examState == ExamState.UNDEFINED.value) {
                viewModel.setVisibleFinishExamButton(true)
            } else {
                viewModel.setVisibleFinishExamButton(false)
            }
            findNavController().navigate(R.id.action_nav_exam_to_nav_detail_exam)
        }

        viewBinding.buttonShuffle.setOnClickListener {
            val builder = AlertDialog.Builder(context)
                .setTitle(DIALOG_TITLE)
                .setMessage(DIALOG_MESSAGE)
                .setPositiveButton(
                    DIALOG_POSITIVE_BUTTON_TEXT
                ) { _, _ -> createExam() }
                .setNegativeButton(DIALOG_NEGATIVE_BUTTON_TEXT) { _, _ ->
                    //Not-op
                }
                .setCancelable(false)
            val dialog = builder.create()
            dialog.show()
        }
    }

    override fun bindData() {
        viewModel.listExam.observe(viewLifecycleOwner) {
            examAdapter.submitList(it)
        }
    }

    private fun createExam() {
        val exam = Exam(id = DEFAULT_ID)
        val listQuestions = viewModel.listQuestions.value
        var tempIndex = FIRST_INDEX

        listQuestions?.let { listQuestions ->
            exam.listQuestions.apply {
                repeat(REPEAT_TIMES) {
                    this.addAll(viewModel.getCategoryList(listQuestions,
                        listTitle[tempIndex],
                        listNumbersOfQuestion[tempIndex]))
                    tempIndex++
                }
            }
            exam.listQuestionOptions.addAll(generateEmptyQuestionStateList(exam.listQuestions.size))
        }

        viewModel.addExamToDatabase(exam)
        showToast(MESSAGE_SUCCESS_ADD_EXAM)
    }

    companion object {
        const val DEFAULT_ID = 0
        const val MESSAGE_SUCCESS_ADD_EXAM = "Thêm đề thi mới thành công"
        const val DIALOG_TITLE = "Thông báo"
        const val DIALOG_MESSAGE = "Bạn có muốn tạo mới 1 đề không ?"
        const val DIALOG_POSITIVE_BUTTON_TEXT = "Có"
        const val DIALOG_NEGATIVE_BUTTON_TEXT = "Không"
        const val REPEAT_TIMES = 4
    }
}
