package com.pedrenrique.githubapp.core.ext

import com.pedrenrique.githubapp.R
import java.io.IOException

val Throwable?.defaultFriendlyTitle: Int
    get() = if (this is IOException) {
        R.string.title_error_connection
    } else {
        R.string.title_error_unexpected
    }

val Throwable?.defaultFriendlyMsg: Int
    get() = if (this is IOException) {
        R.string.msg_error_connection
    } else {
        R.string.msg_error_unexpected
    }

val Throwable?.defaultErrorDrawable: Int
    get() = if (this is IOException) {
        R.drawable.ic_cloud_off
    } else {
        R.drawable.ic_robot_error
    }