@file:JvmName("ViewUtils")

package br.com.concrete.desafio.util

import android.support.v7.app.ActionBar
import android.view.View
import android.view.ViewGroup

fun View.addStatusBarPadding() {
    setPadding(paddingLeft,
            paddingTop + context.statusBarHeight(), paddingRight,
            paddingBottom)
}

fun View.addStatusBarMargin() {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    params.topMargin += context.statusBarHeight()
}

fun ActionBar?.enableBack() {
    if(this == null) return
    setDisplayHomeAsUpEnabled(true)
    setDisplayShowHomeEnabled(true)
}