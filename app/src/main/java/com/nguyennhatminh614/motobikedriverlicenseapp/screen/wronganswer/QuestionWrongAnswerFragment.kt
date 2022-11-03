package com.nguyennhatminh614.motobikedriverlicenseapp.screen.wronganswer

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.nguyennhatminh614.motobikedriverlicenseapp.R
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.QuestionOptions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.StateQuestionOption
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.FragmentQuestionLayoutBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.screen.questions.QuestionOptionAdapter
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseFragment
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.extensions.loadGlideImageFromUrl
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class QuestionWrongAnswerFragment :
    BaseFragment<FragmentQuestionLayoutBinding>(FragmentQuestionLayoutBinding::inflate) {

    private var questionsPosition = AppConstant.NONE_POSITION
    private var questions: Questions? = null
    private var lastSelectedPosition = AppConstant.NONE_POSITION

    override val viewModel by sharedViewModel<WrongAnswerViewModel>()

    private val questionOptionAdapter by lazy { QuestionOptionAdapter() }

    override fun initData() {
        questions?.let {
            viewBinding.apply {
                textTitleQuestions.text = it.question
                textQuestionExplain.text = it.explain

                viewQuestionExplain.visibility = View.INVISIBLE

                if (it.hasImageBanner) {
                    imageQuestions.loadGlideImageFromUrl(
                        context,
                        it.imageUrl,
                        R.drawable.image_main_banner
                    )
                } else {
                    imageQuestions.isVisible = false
                    (recyclerViewQuestionOptions.layoutManager as? ConstraintLayout.LayoutParams)?.topToBottom =
                        textTitleQuestions.id
                }

                viewBinding.recyclerViewQuestionOptions.adapter = questionOptionAdapter
                val initListQuestionOptions = processQuestionOptionsList(it.option)
                questionOptionAdapter.submitList(initListQuestionOptions)
            }
        }
    }

    override fun handleEvent() {
        questionOptionAdapter.registerOnClickItemEvent { item ->
            questionOptionAdapter.updateStateListWithPosition(updateStateListUI(item),
                item.position)

            questions?.let {
                if (item.data == it.answer) {
                    item.stateNumber = StateQuestionOption.CORRECT.type
                } else {
                    if (item.position != AppConstant.NONE_POSITION) {
                        item.stateNumber = StateQuestionOption.INCORRECT.type
                    } else {
                        item.stateNumber = StateQuestionOption.UNKNOWN.type
                    }
                }

                viewModel.updateDataQuestionPos(questionsPosition, item)
            }
        }
    }

    private fun processQuestionOptionsList(listString: List<String>) =
        mutableListOf<QuestionOptions>().apply {
            var index = AppConstant.FIRST_INDEX
            listString.forEach {
                this.add(QuestionOptions(index, it))
                index++
            }
        }

    private fun updateStateListUI(item: QuestionOptions?): MutableList<QuestionOptions> {
        val newList = questionOptionAdapter.currentList.toMutableList()
        if (questions != null && item != null && item.position != AppConstant.NONE_POSITION) {
            val pos = item.position

            if (lastSelectedPosition != AppConstant.NONE_POSITION) {
                newList[lastSelectedPosition].stateNumber = StateQuestionOption.UNKNOWN.type
            }

            if (item.data == questions?.answer) {
                newList[pos].stateNumber = StateQuestionOption.CORRECT.type
                viewBinding.viewQuestionExplain.visibility = View.VISIBLE
            } else {
                newList[pos].stateNumber = StateQuestionOption.INCORRECT.type
                viewBinding.viewQuestionExplain.visibility = View.INVISIBLE
            }

            lastSelectedPosition = pos
        }

        return newList
    }

    override fun bindData() {
        viewModel.listQuestionOptions.observe(viewLifecycleOwner) {
            val data = viewModel.getQuestionOptionSelectedByQuestionPosition(questionsPosition)
            data?.let {
                questionOptionAdapter.updateStateListWithPosition(updateStateListUI(it),
                    it.position)
            }
        }
    }

    companion object {
        fun newInstance(
            questionPosition: Int,
            questions: Questions,
        ) =
            QuestionWrongAnswerFragment().apply {
                this.questionsPosition = questionPosition
                this.questions = questions
            }
    }
}
