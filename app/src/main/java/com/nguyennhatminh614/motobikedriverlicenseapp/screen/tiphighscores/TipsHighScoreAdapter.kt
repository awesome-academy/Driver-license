package com.nguyennhatminh614.motobikedriverlicenseapp.screen.tiphighscores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.TipsHighScore
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.ItemTipsHighScoreLayoutBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.OnClickItem
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseRecyclerViewAdapter
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseViewHolder
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.extensions.processEndline

class TipsHighScoreAdapter :
    BaseRecyclerViewAdapter<TipsHighScore, ItemTipsHighScoreLayoutBinding, TipsHighScoreAdapter.ViewHolder>(
        TipsHighScore.getDiffCallBack()) {

    override fun registerOnClickItemEvent(onClickItem: OnClickItem<TipsHighScore>) {
        this.clickItemInterface = onClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTipsHighScoreLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(
        override val binding: ItemTipsHighScoreLayoutBinding,
    ) : BaseViewHolder<TipsHighScore, ItemTipsHighScoreLayoutBinding>(binding) {
        override fun onBindData(data: TipsHighScore) {
            binding.apply {
                textTipsTitle.text = data.title
                textTipsContent.text = data.content.processEndline()

                root.setOnClickListener {
                    toggleWithAnimation(data, binding)
                }

                buttonSpanDetailContent.setOnClickListener {
                    toggleWithAnimation(data, binding)
                }
            }
        }

        private fun toggleWithAnimation(data: TipsHighScore, binding: ItemTipsHighScoreLayoutBinding) {
            if (data.isVisible) {
                binding.buttonSpanDetailContent.animate().rotation(DEGREE_0)
                binding.expandableView.isVisible = false
            } else {
                binding.buttonSpanDetailContent.animate().rotation(DEGREE_180)
                binding.expandableView.isVisible = true
            }

            data.isVisible = data.isVisible.not()
        }
    }

    companion object {
        const val DEGREE_0 = 0F
        const val DEGREE_180 = 180F
    }
}
