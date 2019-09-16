package com.desafioandroid.core.util

import android.animation.ObjectAnimator
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.dateFormat(date: String): String{
    val format = SimpleDateFormat(date, Locale.US)
    val data = format.parse(this)
    format.applyPattern("dd/MM/yyyy")
    return format.format(data)
}

fun Int.decimalFormat(): String {
    return DecimalFormat("#,###").format(this)
}

fun View.fadeAnimation(){
    this.alpha = 0.3f
    this.animate().setDuration(400)
        .setInterpolator(AccelerateDecelerateInterpolator())
        .alpha(1f)
}

fun View.rotationAnimation(): View{
    ObjectAnimator
        .ofFloat(this, View.ROTATION, 0f, 360f)
        .setDuration(300).start()
    return this
}

fun TextView.textEmptyGone(text: String? = null){
    this.text = text ?: ""
    this.visibility = if (this.text.toString().isEmpty()) View.GONE else View.VISIBLE
}

fun String.addColorSpecificText(context: Context, @ColorRes colorValue: Int, textSpecific: String): SpannableString {
    val color = ContextCompat.getColor(context, colorValue)
    val spannableString = SpannableString(this)
    spannableString.setSpan(ForegroundColorSpan(color), 0, textSpecific.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}