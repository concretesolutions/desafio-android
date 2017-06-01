package br.com.concrete.desafio.animation

import android.transition.Transition

abstract class SimpleTransitionListener : Transition.TransitionListener {
    override fun onTransitionEnd(transition: Transition) {}

    override fun onTransitionResume(transition: Transition) {}

    override fun onTransitionPause(transition: Transition) {}

    override fun onTransitionCancel(transition: Transition) {}

    override fun onTransitionStart(transition: Transition) {}
}