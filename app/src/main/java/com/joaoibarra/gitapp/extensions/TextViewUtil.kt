package com.joaoibarra.gitapp.extensions

import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

fun TextView.formatDate(date: Date?) {
    val format = SimpleDateFormat("dd/MM/yyy")
    this.text = format.format(date).toString()
}