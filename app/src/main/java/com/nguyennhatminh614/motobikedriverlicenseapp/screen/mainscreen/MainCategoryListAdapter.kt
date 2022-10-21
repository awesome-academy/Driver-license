package com.nguyennhatminh614.motobikedriverlicenseapp.screen.mainscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.MainCategoryModel
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.ItemMainScreenCategoryLayoutBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.OnClickItem
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseRecyclerViewAdapter
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseViewHolder

class MainCategoryListAdapter :
    BaseRecyclerViewAdapter<MainCategoryModel, ItemMainScreenCategoryLayoutBinding,
            MainCategoryListAdapter.ViewHolder>(MainCategoryModel.getDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMainScreenCategoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(
        override val binding: ItemMainScreenCategoryLayoutBinding,
    ) : BaseViewHolder<MainCategoryModel, ItemMainScreenCategoryLayoutBinding>(binding){
        override fun onBindData(data: MainCategoryModel) {
            binding.apply {
                imageCategory.setImageResource(data.resourceID)
                textCategory.text = data.title

                itemCategory.setOnClickListener {
                    clickItemInterface?.let { function -> function(data) }
                }
            }
        }
    }

    override fun registerOnClickItemEvent(onClickItem: OnClickItem<MainCategoryModel>) {
        this.clickItemInterface = onClickItem
    }
}
