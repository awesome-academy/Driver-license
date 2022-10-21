package com.nguyennhatminh614.motobikedriverlicenseapp.data.model

import androidx.recyclerview.widget.DiffUtil

class TipsHighScore() {
    var id: Int? = null
    var title: String? = null
    var content: String? = null
    var isVisible: Boolean = false

    constructor(
        id: Int,
        title: String,
        content: String,
        isVisible: Boolean = false,
    ) : this() {
        this.id = id
        this.title = title
        this.content = content
        this.isVisible = isVisible
    }

    companion object {
        fun getDiffCallBack() = object :
            DiffUtil.ItemCallback<TipsHighScore>() {
            override fun areItemsTheSame(
                oldItem: TipsHighScore,
                newItem: TipsHighScore,
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: TipsHighScore,
                newItem: TipsHighScore,
            ): Boolean = oldItem == newItem
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TipsHighScore

        if (id != other.id) return false
        if (title != other.title) return false
        if (content != other.content) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + isVisible.hashCode()
        return result
    }

    override fun toString(): String {
        return "TipsHighScore(id=$id, title=$title, content=$content, isVisible=$isVisible)"
    }
}
