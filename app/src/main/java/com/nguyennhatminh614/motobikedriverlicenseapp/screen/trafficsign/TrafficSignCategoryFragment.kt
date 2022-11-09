package com.nguyennhatminh614.motobikedriverlicenseapp.screen.trafficsign

import android.util.Log
import android.widget.TableLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nguyennhatminh614.motobikedriverlicenseapp.R
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.TrafficSigns
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.FragmentTrafficSignCategoryBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TrafficSignCategoryFragment :
    BaseFragment<FragmentTrafficSignCategoryBinding>(FragmentTrafficSignCategoryBinding::inflate) {

    private val listTrafficSignData = mutableListOf<TrafficSigns>()
    private val adapter by lazy { TrafficSignAdapter() }

    override val viewModel by sharedViewModel<TrafficSignViewModel>()

    override fun initData() {
        viewBinding.recyclerViewTrafficSign.adapter = adapter
        adapter.submitList(listTrafficSignData)
    }

    override fun handleEvent() {
        //Not-op
    }

    override fun bindData() {
        //Not-op
    }

    companion object {
        fun newInstance(listData: MutableList<TrafficSigns>?) =
            TrafficSignCategoryFragment().apply {
                this.listTrafficSignData.clear()
                listData?.let { this.listTrafficSignData.addAll(it) }
            }
    }
}
