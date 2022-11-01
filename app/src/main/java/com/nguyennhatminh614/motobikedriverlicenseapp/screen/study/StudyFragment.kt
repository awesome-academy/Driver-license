package com.nguyennhatminh614.motobikedriverlicenseapp.screen.study

import androidx.navigation.fragment.findNavController
import com.nguyennhatminh614.motobikedriverlicenseapp.R
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.FragmentStudyBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StudyFragment : BaseFragment<FragmentStudyBinding>(FragmentStudyBinding::inflate) {

    override val viewModel by sharedViewModel<StudyViewModel>()

    private val studyAdapter by lazy { StudyAdapter() }

    override fun initData() {
        viewBinding.recyclerViewQuestionCategory.adapter = studyAdapter
    }

    override fun handleEvent() {
        studyAdapter.registerOnClickPositionEvent {
            viewModel.setCurrentStudyCategoryPosition(it)
            findNavController().navigate(R.id.action_nav_study_to_nav_detail_study)
        }
    }

    override fun bindData() {
        viewModel.listStudyCategory.observe(viewLifecycleOwner) {
            studyAdapter.submitList(it)
        }
    }
}
