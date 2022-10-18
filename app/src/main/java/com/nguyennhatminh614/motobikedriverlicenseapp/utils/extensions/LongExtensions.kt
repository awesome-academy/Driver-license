package com.nguyennhatminh614.motobikedriverlicenseapp.utils.extensions

import java.util.concurrent.TimeUnit

const val PATTERN_TIME = "%02d:%02d"

fun Long.toDateTimeMMSS(): String {
    return String.format(
        PATTERN_TIME,
        TimeUnit.MILLISECONDS.toMinutes(this) % TimeUnit.HOURS.toMinutes(1),
        TimeUnit.MILLISECONDS.toSeconds(this) % TimeUnit.HOURS.toMinutes(1)
    )
}
