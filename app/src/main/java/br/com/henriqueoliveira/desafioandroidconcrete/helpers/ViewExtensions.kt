package br.com.henriqueoliveira.desafioandroidconcrete.helpers

import android.view.View
import android.widget.TextView

fun View.show(show: Boolean = true) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun View.invisible(invisible: Boolean = true) {
    visibility = if (!invisible) View.VISIBLE else View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.toggle() {
    this.show(this.visibility != View.VISIBLE)
}

fun TextView.toStr() = this.text.toString()