package com.concrete.desafio_android.extension

import java.text.SimpleDateFormat
import java.util.Date

fun Date.formatString(): String {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy")
    return dateFormat.format(this)
}