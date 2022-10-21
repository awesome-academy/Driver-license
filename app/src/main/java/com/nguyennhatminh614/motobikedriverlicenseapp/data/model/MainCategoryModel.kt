package com.nguyennhatminh614.motobikedriverlicenseapp.data.model

import androidx.recyclerview.widget.DiffUtil

data class MainCategoryModel(
    val resourceID: Int,
    val title: String,
    val type: CategoryType
){
    companion object {
        fun getDiffCallBack() = object :
            DiffUtil.ItemCallback<MainCategoryModel>() {
            override fun areItemsTheSame(
                oldItem: MainCategoryModel,
                newItem: MainCategoryModel,
            ): Boolean = oldItem.resourceID == newItem.resourceID

            override fun areContentsTheSame(
                oldItem: MainCategoryModel,
                newItem: MainCategoryModel,
            ): Boolean = oldItem == newItem
        }
    }
}

enum class CategoryType {
    EXAM,
    STUDY,
    SIGNAL,
    TIPS_HIGH_SCORE,
    WRONG_ANSWER,
    SETTINGS,
}
