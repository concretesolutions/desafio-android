package com.haldny.githubapp.common.extension

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Locale

fun String.dateFormat(date: String): String{
    val format = SimpleDateFormat(date, Locale.US)
    val data = format.parse(this)
    format.applyPattern("dd/MM/yyyy")
    return format.format(data)
}

fun String.addColorSpecificText(context: Context, @ColorRes colorValue: Int,
                                textSpecific: String): SpannableString {
    val color = ContextCompat.getColor(context, colorValue)
    val spannableString = SpannableString(this)
    spannableString.setSpan(ForegroundColorSpan(color), 0,
        textSpecific.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}