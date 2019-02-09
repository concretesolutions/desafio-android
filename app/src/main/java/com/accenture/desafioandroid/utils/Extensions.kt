package com.accenture.desafioandroid.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun ellipsis(str: String, maxSize: Int): String {
    val limit = maxSize - 3
    return if (str.length > maxSize) str.substring(0, limit) + "..." else str
}

fun setDate(date: String): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
    val output = SimpleDateFormat("yyyy-MM-dd HH:mm")
    var d: Date? = null
    try {
        d = sdf.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return output.format(d)
}

fun formatDate(date: String, fromDate: String, toDate: String): String {
    var mDate = ""
    try {
        val myDate = date
        val iFormatter = SimpleDateFormat(fromDate)
        val oFormatter = SimpleDateFormat(toDate)
        mDate = oFormatter.format(iFormatter.parse(myDate))
    } catch (e: Exception) {
        Log.e("error", e.message)
    }

    return mDate

}
