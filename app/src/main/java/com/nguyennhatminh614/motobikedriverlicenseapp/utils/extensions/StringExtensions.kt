package com.nguyennhatminh614.motobikedriverlicenseapp.utils.extensions

fun String?.processEndline() : String {
    if (this == null) {
        return ""
    }
    return this.replace("\\n", System.getProperty("line.separator"))
}
