package com.nguyennhatminh614.motobikedriverlicenseapp.screen.tiphighscores

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.FragmentTipHighScoreBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TipHighScoreFragment :
    BaseFragment<FragmentTipHighScoreBinding>(FragmentTipHighScoreBinding::inflate) {

    override val viewModel by viewModel<TipsHighScoreViewModel>()

    private val adapter by lazy { TipsHighScoreAdapter() }

    override fun handleEvent() {
        // Not-op
    }

    override fun initData() {
        viewBinding.recyclerViewTipsHighScore.adapter = adapter
        viewBinding.recyclerViewTipsHighScore.isVisible = adapter.currentList.isEmpty().not()
        viewBinding.layoutVisibleWhenDataIsEmpty.root.isVisible = adapter.currentList.isEmpty()
    }

    override fun bindData() {
        lifecycleScope.launch {
            viewModel.listTipsHighScore.observe(viewLifecycleOwner) {
                adapter.submitList(it)
                viewBinding.recyclerViewTipsHighScore.isVisible = adapter.currentList.isEmpty().not()
                viewBinding.layoutVisibleWhenDataIsEmpty.root.isVisible = adapter.currentList.isEmpty()
            }
        }
    }
}
