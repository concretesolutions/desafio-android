package br.com.concrete.desafio.statemachine

import android.os.Bundle
import br.com.concrete.desafio.STATE_MACHINE_CURRENT_KEY

/**
 * Simple finite state machine for view stateMap.
 * This class have the necessary methods to do ViewState transitions
 * Extends this class and implements performChangeState method to make ViewState transitions
 */
abstract class StateMachine<T> {

    internal val stateMap = HashMap<Int, T>()

    private var onChangeState: ((key: Int) -> Unit)? = null
    var currentStateKey: Int = -1
        private set

    /**
     * The implementation to change the state

     * @param state
     */
    protected abstract fun performChangeState(state: T)

    /**
     * The implementation to Create a State

     * @return new State instance
     */
    protected abstract fun createState(): T

    fun onChangeState(onChangeState: ((key: Int) -> Unit)) {
        this.onChangeState = onChangeState
    }

    fun changeState(stateKey: Int, forceChange: Boolean = false, onChangeState: ((key: Int) -> Unit)? = this.onChangeState) {

        if (stateKey == currentStateKey && !forceChange) return

        performChangeState(stateMap[stateKey]!!)
        // On change state
        onChangeState?.invoke(stateKey)

        currentStateKey = stateKey
    }

    fun restoreInstanceState(savedInstanceState: Bundle) {
        currentStateKey = savedInstanceState.getInt(STATE_MACHINE_CURRENT_KEY)
    }

    fun saveInstanceState(): Bundle {
        val bundle = Bundle()
        bundle.putInt(STATE_MACHINE_CURRENT_KEY, currentStateKey)
        return bundle
    }

    fun add(key: Int, state: T.() -> Unit) = stateMap.put(key, createState().apply { state() })

    inline fun setup(initialState: Int, restoreState: Bundle? = null, func: StateMachine<T>.() -> Unit) {
        func()
        val state = if (currentStateKey != -1) currentStateKey else initialState
        changeState(restoreState?.getInt(STATE_MACHINE_CURRENT_KEY) ?: state, forceChange = true)
    }

}