package br.com.concrete.desafio.statemachine

import android.databinding.DataBindingUtil.bind
import android.databinding.ViewDataBinding
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager.go
import br.com.concrete.desafio.animation.SimpleTransitionListener

/**
 * Implementation of [StateMachine]
 * This implementation uses the Scene framework to make State transitions
 */
class SceneStateMachine : StateMachine<SceneState>() {

    override fun performChangeState(state: SceneState) {
        val currentState = get(currentStateKey)
        callAction(currentState?.scene, currentState?.exit)

        if (state.transition == null) {
            state.scene?.enter()
            callAction(state.scene, state.enter)
        } else
            go(state.scene, state.transition?.addListener(object : SimpleTransitionListener() {
                override fun onTransitionEnd(transition: Transition) {
                    callAction(state.scene, state.enter)
                    transition.removeListener(this)
                }
            }))
    }

    override fun createState() = SceneState()

    private fun callAction(scene: Scene?, action: ((binding: ViewDataBinding) -> Unit)?) {
        val root = scene?.sceneRoot?.getChildAt(0)
        if (root != null) action?.invoke(bind(root))
    }

}