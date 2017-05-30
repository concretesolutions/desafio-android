package br.com.concrete.desafio

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast


fun <T : ViewModel> LifecycleActivity.initViewModel(modelClass: Class<T>): T {
    return ViewModelProviders.of(this).get(modelClass)
}

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toast(@StringRes msgRes: Int) = Toast.makeText(this, msgRes, Toast.LENGTH_SHORT).show()