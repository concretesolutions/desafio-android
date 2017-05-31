package br.com.concrete.desafio.statemachine

import android.view.View

class ViewState {
    val visibles = emptyArray<View>()
    val gones = emptyArray<View>()
    val invisibles = emptyArray<View>()
    var enter: (() -> Unit)? = null
        private set
    var exit: (() -> Unit)? = null
        private set

    fun onEnter(func: () -> Unit) {
        enter = func
    }

    fun onExit(func: () -> Unit) {
        exit = func
    }

    fun visibles(vararg views: View) {
        visibles.plus(views)
    }

    fun invisibles(vararg views: View) {
        invisibles.plus(views)
    }

    fun gones(vararg views: View) {
        gones.plus(views)
    }

}