package com.nguyennhatminh614.motobikedriverlicenseapp.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TrafficSigns : Parcelable {
    var id = ""
    var title = ""
    var description = ""

    @set:PropertyName("image_url")
    @get:PropertyName("image_url")
    var imageUrl = ""

    var category = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TrafficSigns

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (imageUrl != other.imageUrl) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }

    override fun toString(): String {
        return "TrafficSigns(id='$id', title='$title', description='$description', " +
                "imageUrl='$imageUrl', category='$category')"
    }

    companion object {
        fun getDiffUtilCallback() =
            object : DiffUtil.ItemCallback<TrafficSigns>() {
                override fun areItemsTheSame(
                    oldItem: TrafficSigns,
                    newItem: TrafficSigns,
                ): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: TrafficSigns,
                    newItem: TrafficSigns,
                ): Boolean = oldItem == newItem
            }
    }
}

enum class TrafficSignCategory(val value: String) {
    RESTRICT_TRAFFIC_SIGNAL(Constant.RESTRICT_TRAFFIC_SIGNAL_VALUE),
    COMMAND_TRAFFIC_SIGNAL(Constant.COMMAND_TRAFFIC_SIGNAL_VALUE),
    INSTRUCTION_TRAFFIC_SIGNAL(Constant.INSTRUCTION_TRAFFIC_SIGNAL_VALUE),
    SUB_TRAFFIC_SIGNAL(Constant.SUB_TRAFFIC_SIGNAL_VALUE),
    WARNING_TRAFFIC_SIGNAL(Constant.WARNING_TRAFFIC_SIGNAL_VALUE);

    object Constant {
        const val RESTRICT_TRAFFIC_SIGNAL_VALUE = "RestrictTrafficSign"
        const val COMMAND_TRAFFIC_SIGNAL_VALUE = "CommandTrafficSignal"
        const val INSTRUCTION_TRAFFIC_SIGNAL_VALUE = "InstructionTrafficSignal"
        const val SUB_TRAFFIC_SIGNAL_VALUE = "SubTrafficSignal"
        const val WARNING_TRAFFIC_SIGNAL_VALUE = "WarningTrafficSignal"
    }
}
