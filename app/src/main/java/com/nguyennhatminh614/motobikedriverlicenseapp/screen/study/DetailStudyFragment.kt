package com.nguyennhatminh614.motobikedriverlicenseapp.screen.study

import androidx.viewpager2.widget.ViewPager2
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.QuestionOptions
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.FragmentDetailStudyLayoutBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.adapter.ViewPagerAdapter
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseFragment
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.dialog.BottomSheetQuestionDialog
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IBottomSheetListener
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailStudyFragment :
    BaseFragment<FragmentDetailStudyLayoutBinding>(FragmentDetailStudyLayoutBinding::inflate) {

    private var listQuestionSize = AppConstant.EMPTY_SIZE
    override val viewModel by sharedViewModel<StudyViewModel>()

    private val bottomSheetDialog by lazy {
        BottomSheetQuestionDialog()
    }

    private val questionAdapter by lazy {
        ViewPagerAdapter(parentFragmentManager, lifecycle)
    }

    override fun initData() {
        viewBinding.viewPagerQuestions.adapter = questionAdapter
    }

    override fun handleEvent() {
        viewBinding.buttonNextQuestion.setOnClickListener {
            if (viewBinding.viewPagerQuestions.currentItem < listQuestionSize) {
                viewBinding.viewPagerQuestions.currentItem++
            }
        }

        viewBinding.viewPagerQuestions.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewBinding.textCurrentQuestions.text =
                        "Câu ${viewBinding.viewPagerQuestions.currentItem + 1}/${listQuestionSize}"
                }
            }
        )

        viewBinding.buttonPreviousQuestion.setOnClickListener {
            if (viewBinding.viewPagerQuestions.currentItem > AppConstant.FIRST_INDEX) {
                viewBinding.viewPagerQuestions.currentItem--
            }
        }

        viewBinding.bottomBar.setOnClickListener {
            context?.let { notNullContext ->
                bottomSheetDialog.showDialog(
                    notNullContext,
                    viewModel.listStudyCategory.value?.get(viewModel.currentStudyCategory.value
                        ?: AppConstant.NONE_POSITION)
                        ?.listQuestionsState,
                    viewBinding.viewPagerQuestions.currentItem,
                    object : IBottomSheetListener {
                        override fun onNextQuestion(listener: IResponseListener<Int>) {
                            if (viewBinding.viewPagerQuestions.currentItem < listQuestionSize) {
                                viewBinding.viewPagerQuestions.currentItem++
                                listener.onSuccess(viewBinding.viewPagerQuestions.currentItem)
                            } else {
                                listener.onError(null)
                            }
                        }

                        override fun onPreviousQuestion(listener: IResponseListener<Int>) {
                            if (viewBinding.viewPagerQuestions.currentItem > AppConstant.FIRST_INDEX) {
                                viewBinding.viewPagerQuestions.currentItem--
                                listener.onSuccess(viewBinding.viewPagerQuestions.currentItem)
                            } else {
                                listener.onError(null)
                            }
                        }

                        override fun onClickMoveToPosition(index: Int, data: QuestionOptions) {
                            viewBinding.viewPagerQuestions.currentItem = index
                        }

                    }, listQuestionSize)
            }
        }
    }

    override fun bindData() {
        viewModel.listStudyCategory.observe(viewLifecycleOwner) {
            val currentPosition =
                viewModel.currentStudyCategory.value ?: AppConstant.NONE_POSITION
            listQuestionSize = it[currentPosition].totalNumberOfQuestions

            var index = AppConstant.FIRST_INDEX
            it[currentPosition].listQuestions.forEach { question ->
                questionAdapter.addFragment(
                    QuestionStudyFragment.newInstance(index, question)
                )
                index++
            }
        }
    }
}
