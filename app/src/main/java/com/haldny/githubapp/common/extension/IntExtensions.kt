package com.haldny.githubapp.common.extension

import java.text.DecimalFormat

fun Int.decimalFormat(): String {
    return DecimalFormat("#,###").format(this)
}