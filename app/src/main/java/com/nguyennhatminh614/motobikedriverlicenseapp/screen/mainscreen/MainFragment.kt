package com.nguyennhatminh614.motobikedriverlicenseapp.screen.mainscreen

import androidx.navigation.fragment.findNavController
import com.nguyennhatminh614.motobikedriverlicenseapp.R
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.CategoryType
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.MainCategoryModel
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.FragmentMainBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseFragment
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override val viewModel by viewModel<BaseViewModel>()

    private val categories = listOf(
        MainCategoryModel(R.drawable.ic_exam, EXAM_CATEGORY, CategoryType.EXAM),
        MainCategoryModel(R.drawable.ic_study, STUDY_CATEGORY, CategoryType.STUDY),
        MainCategoryModel(R.drawable.ic_signal, SIGNAL_CATEGORY, CategoryType.SIGNAL),
        MainCategoryModel(R.drawable.ic_tips_high_score,
            TIPS_HIGH_SCORE_CATEGORY,
            CategoryType.TIPS_HIGH_SCORE),
        MainCategoryModel(R.drawable.ic_wrong_answer,
            WRONG_ANSWER_CATEGORY,
            CategoryType.WRONG_ANSWER),
        MainCategoryModel(R.drawable.ic_settings, SETTINGS_CATEGORY, CategoryType.SETTINGS),
    )

    private val mainAdapter by lazy { MainCategoryListAdapter() }

    override fun initData() {
        // Not support
    }

    override fun handleEvent() {
        mainAdapter.registerOnClickItemEvent {
            val navigateIDAction = when (it.type) {
                CategoryType.EXAM -> R.id.action_nav_main_to_nav_exam
                CategoryType.STUDY -> R.id.action_nav_main_to_nav_study
                CategoryType.SIGNAL -> R.id.action_nav_main_to_nav_traffic_sign
                CategoryType.TIPS_HIGH_SCORE -> R.id.action_nav_main_to_nav_tips_high_score
                CategoryType.WRONG_ANSWER -> R.id.action_nav_main_to_nav_wrong_answer
                CategoryType.SETTINGS -> R.id.action_nav_main_to_nav_settings
            }

            findNavController().navigate(navigateIDAction)
        }
    }

    override fun bindData() {
        viewBinding.recyclerViewCategory.adapter = mainAdapter
        mainAdapter.submitList(categories)
    }

    companion object {
        private const val EXAM_CATEGORY = "Thi sát hạch"
        private const val STUDY_CATEGORY = "Học lý thuyết"
        private const val SIGNAL_CATEGORY = "Biển báo đường bộ"
        private const val TIPS_HIGH_SCORE_CATEGORY = "Mẹo điểm cao"
        private const val WRONG_ANSWER_CATEGORY = "Các câu làm sai"
        private const val SETTINGS_CATEGORY = "Cài đặt"
    }
}
