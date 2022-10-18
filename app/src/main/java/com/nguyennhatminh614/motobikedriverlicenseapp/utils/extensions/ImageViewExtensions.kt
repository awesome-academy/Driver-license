package com.nguyennhatminh614.motobikedriverlicenseapp.utils.extensions

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadGlideImageFromUrl(context: Context?, urlString: String, defaultImageID: Int) {
    context?.let {
        Glide.with(it)
            .load(urlString)
            .placeholder(defaultImageID)
            .into(this)
    }
}
