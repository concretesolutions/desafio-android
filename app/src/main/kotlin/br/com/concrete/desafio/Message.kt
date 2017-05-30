package br.com.concrete.desafio

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toast(@StringRes msgRes: Int) = Toast.makeText(this, msgRes, Toast.LENGTH_SHORT).show()