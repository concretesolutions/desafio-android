package br.com.concrete.desafio.statemachine

import android.databinding.ViewDataBinding
import android.transition.Scene
import android.transition.Transition
import android.view.ViewGroup

class SceneState {

    var scene: Scene? = null
        private set
    var transition: Transition? = null
        private set
    var start: (() -> Unit)? = null
        private set
    var enter: ((binding: ViewDataBinding) -> Unit)? = null
        private set
    var exit: ((binding: ViewDataBinding) -> Unit)? = null
        private set

    fun onStart(func: () -> Unit) {
        start = func
    }

    fun onEnter(func: (binding: ViewDataBinding) -> Unit) {
        enter = func
    }

    fun onExit(func: (binding: ViewDataBinding) -> Unit) {
        exit = func
    }

    fun scene(pair: Pair<Int, ViewGroup>) {
        scene = Scene.getSceneForLayout(pair.second, pair.first, pair.second.context)
    }

    fun transition(transition: Transition) {
        this.transition = transition
    }

}