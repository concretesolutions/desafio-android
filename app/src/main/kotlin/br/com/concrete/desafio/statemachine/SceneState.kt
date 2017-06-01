package br.com.concrete.desafio.statemachine

import android.transition.Scene
import android.transition.Transition
import android.view.ViewGroup
import br.com.concrete.desafio.repo.RepoListActivity
import kotlin.reflect.KFunction1

class SceneState {

    var scene: Scene? = null
        private set
    var transition: Transition? = null
        private set
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

    fun scene(pair: Pair<Int, ViewGroup>) {
        scene = Scene.getSceneForLayout(pair.second, pair.first, pair.second.context)
    }

    fun transition(transition: Transition) {
        this.transition = transition
    }

}