@file:JvmName("MessageUtils")
package br.com.concrete.desafio

import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.widget.Toast

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toast(@StringRes msgRes: Int) = Toast.makeText(this, msgRes, Toast.LENGTH_SHORT).show()

fun Context.alert(@StringRes title: Int, @StringRes message: Int) = AlertDialog.Builder(this).setTitle(title).setMessage(message).show()

fun Context.alert(title: String?, message: String?) = AlertDialog.Builder(this).setTitle(title).setMessage(message).show()