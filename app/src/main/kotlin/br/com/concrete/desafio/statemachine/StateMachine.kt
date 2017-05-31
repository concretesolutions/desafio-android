package br.com.concrete.desafio.statemachine

import android.os.Bundle

/**
 * Simple finite state machine for view states.
 * This class have the necessary methods to do ViewState transitions
 * Extends this class and implements performChangeState method to make ViewState transitions
 */
abstract class StateMachine<T> : HashMap<Int, T>() {

    private val CURRENT_KEY = "StateMachine.CurrentKey"

    private var onChangeState: ((key: Int) -> Unit)? = null
    protected var currentStateKey: Int = 0

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

    fun changeState(stateKey: Int, onChangeState: ((key: Int) -> Unit)?) {

        performChangeState(get(stateKey)!!)
        // On change state
        onChangeState?.invoke(stateKey)

        currentStateKey = stateKey
    }


    fun restoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) return
        currentStateKey = savedInstanceState.getInt(CURRENT_KEY)
    }

    fun saveInstanceState(): Bundle {
        val bundle = Bundle()
        bundle.putInt(CURRENT_KEY, currentStateKey)
        return bundle
    }

    inline fun setup(func: StateMachine<T>.() -> Unit) = func()

    inline fun add(key: Int, state: T.() -> Unit) = put(key, createState().apply { state() })

}
