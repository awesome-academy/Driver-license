package com.nguyennhatminh614.motobikedriverlicenseapp.screen.exam

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nguyennhatminh614.motobikedriverlicenseapp.R
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Exam
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.ExamState
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.ItemExamListLayoutBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.OnClickItem
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseRecyclerViewAdapter
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseViewHolder

class ExamAdapter :
    BaseRecyclerViewAdapter<Exam, ItemExamListLayoutBinding, ExamAdapter.ViewHolder>(Exam.getDiffCallBack()) {

    private var doExamButtonCallBack: OnClickItem<Int>? = null

    override fun registerOnClickItemEvent(onClickItem: OnClickItem<Exam>) {
        this.clickItemInterface = onClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExamListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    fun registerOnClickExamButtonEvent(onClickItem: OnClickItem<Int>) {
        this.doExamButtonCallBack = onClickItem
    }

    private fun getSelectedColor(binding: ItemExamListLayoutBinding, color: Int) =
        binding.root.context.resources.getColor(color)

    companion object {
        private const val WATCH_RESULT = "Xem lại"
    }

    inner class ViewHolder(
        override val binding: ItemExamListLayoutBinding,
    ) : BaseViewHolder<Exam, ItemExamListLayoutBinding>(binding) {

        override fun onBindData(data: Exam) {
            binding.apply {
                textExamTitle.text = "Đề số ${data.id}"

                val idColor = when (data.examState) {
                    ExamState.PASSED.value -> R.color.green_pastel
                    ExamState.FAILED.value -> R.color.red_pastel
                    else -> R.color.white
                }

                root.setCardBackgroundColor(getSelectedColor(binding, idColor))

                if (data.examState != ExamState.UNDEFINED.value) {
                    buttonDoExam.text = WATCH_RESULT
                }

                buttonDoExam.setOnClickListener {
                    doExamButtonCallBack?.let { function ->
                        function(adapterPosition)
                    }
                }
            }
        }
    }
}
