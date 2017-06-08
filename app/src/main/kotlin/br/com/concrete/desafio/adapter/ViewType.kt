package br.com.concrete.desafio.adapter

import android.view.View

open class ViewType<T>(var layout: Int) {

    var rule: ((Int, List<T>) -> Boolean) = { _, _ -> true }
        private set
    var click: ((Int, T, View) -> Unit)? = null
        private set
    var bind: ((Int, T?, View) -> Unit)? = null
        private set

    fun click(click: (Int, T, View) -> Unit) {
        this.click = click
    }

    fun rule(rule: (Int, List<T>) -> Boolean) {
        this.rule = rule
    }

    fun bind(bind: (Int, T?, View) -> Unit) {
        this.bind = bind
    }

}