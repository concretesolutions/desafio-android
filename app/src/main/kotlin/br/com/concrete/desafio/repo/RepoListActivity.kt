package br.com.concrete.desafio.repo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.transition.Transition
import br.com.concrete.desafio.R
import br.com.concrete.desafio.statemachine.SceneStateMachine
import br.com.concrete.sdk.RepoRepository
import kotlinx.android.synthetic.main.activity_repo_list.*
import kotlinx.android.synthetic.main.sc_default_list.*

class RepoListActivity : AppCompatActivity() {

    val STATE_MACHINE = "STATE_MACHINE"
    val stateMachine = SceneStateMachine()
    val fade: Transition = Fade()

    val onEnterLoading: () -> Unit = {
        RepoRepository.search(0).subscribe(
                {
                    if (it.items.isNotEmpty()) stateMachine.changeState(LIST_STATE)
                    else stateMachine.changeState(EMPTY_STATE)
                },
                { stateMachine.changeState(ERROR_STATE) })
    }

    val onEnterList: () -> Unit = {
        recyclerView.setBackgroundColor(Color.BLACK)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        setupStateMachine(savedInstanceState?.getBundle(STATE_MACHINE))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(STATE_MACHINE, stateMachine.saveInstanceState())
    }

    private fun setupStateMachine(savedInstanceState: Bundle?) {
        stateMachine.setup(initalState = LOADING_STATE, restoreState = savedInstanceState) {
            add(LOADING_STATE) {
                scene(R.layout.sc_default_loading to mainRoot)
                transition(fade)
                onEnter(onEnterLoading)
            }
            add(LIST_STATE) {
                scene(R.layout.sc_default_list to mainRoot)
                transition(fade)
                onEnter(onEnterList)
            }
            add(EMPTY_STATE) {
                scene(R.layout.sc_repo_list_empty to mainRoot)
                transition(fade)
            }
            add(ERROR_STATE) {
                scene(R.layout.sc_repo_list_error to mainRoot)
                transition(fade)
            }
        }
    }

}