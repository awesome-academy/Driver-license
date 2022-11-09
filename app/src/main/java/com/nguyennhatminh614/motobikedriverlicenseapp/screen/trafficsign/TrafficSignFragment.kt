package com.nguyennhatminh614.motobikedriverlicenseapp.screen.trafficsign

import com.google.android.material.tabs.TabLayoutMediator
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.TrafficSignCategory
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.FragmentTrafficSignBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.adapter.ViewPagerAdapter
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrafficSignFragment :
    BaseFragment<FragmentTrafficSignBinding>(FragmentTrafficSignBinding::inflate) {

    private val listTab = listOf(
        RESTRICT_TRAFFIC_SIGNAL,
        COMMAND_TRAFFIC_SIGNAL,
        INSTRUCTION_TRAFFIC_SIGNAL,
        SUB_TRAFFIC_SIGNAL,
        WARNING_TRAFFIC_SIGNAL,
    )

    private val listCategory = listOf(
        TrafficSignCategory.RESTRICT_TRAFFIC_SIGNAL.value,
        TrafficSignCategory.COMMAND_TRAFFIC_SIGNAL.value,
        TrafficSignCategory.INSTRUCTION_TRAFFIC_SIGNAL.value,
        TrafficSignCategory.SUB_TRAFFIC_SIGNAL.value,
        TrafficSignCategory.WARNING_TRAFFIC_SIGNAL.value,
    )

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(parentFragmentManager, lifecycle)
    }

    override val viewModel by viewModel<TrafficSignViewModel>()

    override fun initData() {
        viewBinding.viewPagerTrafficSign.adapter = viewPagerAdapter
        viewBinding.tabLayoutTrafficSignCategory.removeAllTabs()
        listTab.forEach {
            viewBinding.tabLayoutTrafficSignCategory
                .addTab(viewBinding.tabLayoutTrafficSignCategory.newTab()
                    .apply { text = it })
        }

        TabLayoutMediator(viewBinding.tabLayoutTrafficSignCategory,
            viewBinding.viewPagerTrafficSign) { tab, position ->
            tab.text = listTab[position]
        }.attach()
    }

    override fun handleEvent() {
        //Not-op
    }

    override fun bindData() {
        viewModel.listTrafficSign.observe(viewLifecycleOwner) {
            viewPagerAdapter.clearAllFragments()

            listCategory.forEach { category ->
                val data = viewModel.getListByCategory(category)
                viewPagerAdapter.addFragment(
                    TrafficSignCategoryFragment.newInstance(data)
                )
            }
        }
    }

    companion object {
        private const val RESTRICT_TRAFFIC_SIGNAL = "Biển báo cấm"
        private const val COMMAND_TRAFFIC_SIGNAL = "Biển báo hiệu lệnh"
        private const val INSTRUCTION_TRAFFIC_SIGNAL = "Biển báo chỉ dẫn"
        private const val SUB_TRAFFIC_SIGNAL = "Biển báo phụ"
        private const val WARNING_TRAFFIC_SIGNAL = "Biển báo cảnh báo"
    }
}
