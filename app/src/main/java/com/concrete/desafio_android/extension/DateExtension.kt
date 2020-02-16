package com.concrete.desafio_android.extension

import java.text.SimpleDateFormat
import java.util.Date

fun Date.formatString(): String{
    val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
    return dateFormat.format(this)
}