package br.com.concrete.desafio.statemachine

import android.transition.Transition
import android.transition.TransitionManager.go
import br.com.concrete.desafio.animation.SimpleTransitionListener

/**
 * Implementation of [StateMachine]
 * This implementation uses the Scene framework to make State transitions
 */
class SceneStateMachine : StateMachine<SceneState>() {

    override fun performChangeState(state: SceneState) {
        stateMap[currentStateKey]?.exit?.invoke()

        val attached = if (state.scene == null) false else state.scene!!.sceneRoot.isAttachedToWindow
        if (state.transition == null || !attached) {
            state.scene?.enter()
            state.enter?.invoke()
        } else
            go(state.scene, state.transition?.clone()?.addListener(object : SimpleTransitionListener() {
                override fun onTransitionEnd(transition: Transition) {
                    state.enter?.invoke()
                    transition.removeListener(this)
                }
            }))
    }

    override fun createState() = SceneState()

}