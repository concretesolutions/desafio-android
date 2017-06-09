@file:JvmName("ContextUtils")

package br.com.concrete.desafio.util

import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.widget.Toast

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toast(@StringRes msgRes: Int) = Toast.makeText(this, msgRes, Toast.LENGTH_SHORT).show()

fun Context.alert(@StringRes title: Int, @StringRes message: Int) = AlertDialog.Builder(this).setTitle(title).setMessage(message).show()

fun Context.alert(title: String?, message: String?) = AlertDialog.Builder(this).setTitle(title).setMessage(message).show()

fun Context.statusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) result = resources.getDimensionPixelSize(resourceId)
    return result
}