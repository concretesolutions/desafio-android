package br.com.concrete.desafio.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.widget.LinearLayout
import br.com.concrete.desafio.R
import br.com.concrete.desafio.statemachine.SceneStateMachine
import br.com.concrete.desafio.statemachine.ViewStateMachine
import br.com.concrete.desafio.toast
import br.com.concrete.sdk.RepoRepository

class RepoListActivity : AppCompatActivity() {

    val viewState = ViewStateMachine()
    val sceneState = SceneStateMachine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RepoRepository.search(0).subscribe(
                { toast("$it") },
                { toast("$it") })

        viewState.setup {
            onChangeState { toast("$it") }
            add(LOADING_STATE) {
                visibles(botao1, botao2)
                invisibles()
                gones()
                onEnter { }
                onExit { }
            }
        }

        sceneState.setup {
            onChangeState { toast("$it") }
            add(LOADING_STATE) {
                scene(R.layout.activity_main to rootView)
                transition(Fade())
                onEnter { }
                onExit { }
            }
        }

    }

}