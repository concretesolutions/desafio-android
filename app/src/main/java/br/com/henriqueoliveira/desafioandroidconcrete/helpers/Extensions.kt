package br.com.henriqueoliveira.desafioandroidconcrete.helpers

import android.app.Activity
import android.content.Context
import android.widget.Toast
import android.view.Gravity



fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    val toast = Toast.makeText(this, message, length)
    toast.setGravity(Gravity.BOTTOM, 0, 0)
    toast.show()
}

fun Activity.showSnack(text: String, lenght: Int = com.google.android.material.snackbar.Snackbar.LENGTH_SHORT) {
    com.google.android.material.snackbar.Snackbar.make(findViewById(android.R.id.content), text, lenght).show()
}

fun String.toHtmlColored(hexColor: String): String{

    return "<font color=$hexColor>$this</font>"

}