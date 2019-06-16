package com.abs.javarepos.view.util

import android.view.View

object FadeUtils {

    fun fadeIn(view: View?) {
        view?.animate()?.apply {
            alpha(1f)
            duration = 1000
        }
    }

    fun fadeOut(view: View?) {
        view?.animate()?.apply {
            alpha(0f)
            duration = 1000
        }
    }
}