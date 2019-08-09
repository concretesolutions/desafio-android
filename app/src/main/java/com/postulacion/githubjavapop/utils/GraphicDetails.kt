package com.postulacion.githubjavapop.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.Window
import android.view.WindowManager
import com.postulacion.githubjavapop.R

fun changeColorInitialsViews(context: Context, window: Window) {
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(context, R.color.colorPrimary)
}