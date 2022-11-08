package com.nguyennhatminh614.motobikedriverlicenseapp.screen.settings

import android.content.Context.MODE_PRIVATE
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.FragmentSettingsBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseFragment
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    override val viewModel by sharedViewModel<SettingsViewModel>()

    override fun initData() {
        //Not-op
    }

    override fun handleEvent() {
        viewBinding.switchDarkMode.setOnClickListener {
            if (viewBinding.switchDarkMode.isChecked.not()) {
                viewModel.turnOffDarkMode()
            } else {
                viewModel.turnOnDarkMode()
            }
        }
    }

    override fun bindData() {
        viewModel.isDarkModeOn.observe(viewLifecycleOwner) {
            viewBinding.switchDarkMode.isChecked = it
        }
    }
}
