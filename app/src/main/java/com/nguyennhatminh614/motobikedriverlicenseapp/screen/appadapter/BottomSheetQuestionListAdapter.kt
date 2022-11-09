package com.nguyennhatminh614.motobikedriverlicenseapp.screen.appadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.nguyennhatminh614.motobikedriverlicenseapp.R
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.QuestionOptions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.StateQuestionOption
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.ItemExamQuestionBottomSheetDialogBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.OnClickItem
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.OnClickItemPosition
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseRecyclerViewAdapter
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseViewHolder
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant.NONE_POSITION

class BottomSheetQuestionListAdapter :
    BaseRecyclerViewAdapter<QuestionOptions, ItemExamQuestionBottomSheetDialogBinding,
            BottomSheetQuestionListAdapter.ViewHolder>(
        QuestionOptions.getDiffUtilCallBack()) {

    private var currentQuestionIndex = NONE_POSITION
    private var onClickItemPosition: OnClickItemPosition<QuestionOptions>? = null

    fun registerOnClickItemPositionEvent(onClickItem: OnClickItemPosition<QuestionOptions>) {
        this.onClickItemPosition = onClickItem
    }

    override fun registerOnClickItemEvent(onClickItem: OnClickItem<QuestionOptions>) {
        this.clickItemInterface = onClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemExamQuestionBottomSheetDialogBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)

        return ViewHolder(binding)
    }

    private fun getSelectedColor(binding: ItemExamQuestionBottomSheetDialogBinding, color: Int) =
        binding.root.context.resources.getColor(color)

    fun setSingleSelection(adapterPosition: Int) {
        currentQuestionIndex = adapterPosition
        submitList(currentList)
        notifyDataSetChanged()
    }

    companion object {
        const val SELECTED_COLOR = R.color.primary_color
        const val UNSELECTED_COLOR = R.color.transparent
        const val STATE_UNKNOWN_COLOR = R.color.white
        const val STATE_INCORRECT_COLOR = R.color.red_pastel
        const val STATE_CORRECT_COLOR = R.color.green_pastel
    }

    inner class ViewHolder(
        override val binding: ItemExamQuestionBottomSheetDialogBinding,
    ) : BaseViewHolder<QuestionOptions, ItemExamQuestionBottomSheetDialogBinding>(binding) {
        override fun onBindData(item: QuestionOptions) {
            binding.apply {
                val currentPosition = (adapterPosition + 1).toString()
                val currentQuestionOptionSelectedPos = (item.position + 1).toString()

                textQuestionIndex.text = currentPosition

                if (item.position != NONE_POSITION) {
                    itemBadgeLayout.isVisible = true
                    textSelectionState.text = currentQuestionOptionSelectedPos
                } else {
                    itemBadgeLayout.isVisible = false
                }

                when (item.stateNumber) {
                    StateQuestionOption.UNKNOWN.type -> {
                        itemQuestionIcon.setCardBackgroundColor(getSelectedColor(binding,
                            STATE_UNKNOWN_COLOR))
                    }
                    StateQuestionOption.INCORRECT.type -> {
                        itemQuestionIcon.setCardBackgroundColor(getSelectedColor(binding,
                            STATE_INCORRECT_COLOR))
                    }
                    StateQuestionOption.CORRECT.type -> {
                        itemQuestionIcon.setCardBackgroundColor(getSelectedColor(binding,
                            STATE_CORRECT_COLOR))
                    }
                }

                root.setOnClickListener {
                    onClickItemPosition?.let { function -> function(adapterPosition, item) }
                }
            }

            if (currentQuestionIndex == adapterPosition) {
                binding.itemQuestionIcon.strokeColor =
                    getSelectedColor(binding, SELECTED_COLOR)
            } else {
                binding.itemQuestionIcon.strokeColor =
                    getSelectedColor(binding, UNSELECTED_COLOR)
            }
        }
    }
}
