package br.com.concrete.desafio.statemachine

import android.os.Bundle

/**
 * Simple finite state machine for view states.
 * This class have the necessary methods to do ViewState transitions
 * Extends this class and implements performChangeState method to make ViewState transitions
 */
abstract class StateMachine<T> : HashMap<Int, T>() {

    companion object {
        const val CURRENT_KEY = "StateMachine.CurrentKey"
    }

    private var onChangeState: ((key: Int) -> Unit)? = null
    var currentStateKey: Int = -1

    /**
     * The implementation to change the state

     * @param state
     */
    protected abstract fun performChangeState(state: T)

    /**
     * The implementation to Create a State

     * @param state
     */
    abstract fun createState(): T


    fun onChangeState(onChangeState: ((key: Int) -> Unit)) {
        this.onChangeState = onChangeState
    }

    fun changeState(stateKey: Int, onChangeState: ((key: Int) -> Unit)? = this.onChangeState) {

        if (stateKey == currentStateKey) return

        performChangeState(get(stateKey)!!)
        // On change state
        onChangeState?.invoke(stateKey)

        currentStateKey = stateKey
    }


    fun restoreInstanceState(savedInstanceState: Bundle) {
        currentStateKey = savedInstanceState.getInt(CURRENT_KEY)
    }

    fun saveInstanceState(): Bundle {
        val bundle = Bundle()
        bundle.putInt(CURRENT_KEY, currentStateKey)
        return bundle
    }

    inline fun setup(initalState: Int, restoreState: Bundle? = null, func: StateMachine<T>.() -> Unit) {
        this.func()
        changeState(restoreState?.getInt(CURRENT_KEY) ?: initalState)
    }

    inline fun add(key: Int, state: T.() -> Unit) = put(key, createState().apply { state() })

}
