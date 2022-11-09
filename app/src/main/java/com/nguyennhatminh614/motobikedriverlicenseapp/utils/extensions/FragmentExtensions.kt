package com.nguyennhatminh614.motobikedriverlicenseapp.utils.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String, isLongDuration: Boolean = false) {
    Toast.makeText(
        context,
        message,
        if (isLongDuration) Toast.LENGTH_LONG
        else Toast.LENGTH_SHORT
    ).show()
}
