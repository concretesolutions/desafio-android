package br.com.concrete.desafio.feature.repo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.Fade
import android.transition.Transition
import br.com.concrete.desafio.*
import br.com.concrete.desafio.adapter.PaginatingRecyclerAdapter
import br.com.concrete.desafio.statemachine.SceneStateMachine
import br.com.concrete.sdk.RepoRepository
import br.com.concrete.sdk.model.Repo
import kotlinx.android.synthetic.main.activity_repo_list.*
import kotlinx.android.synthetic.main.sc_default_list.*

class RepoListActivity : AppCompatActivity() {

    private val stateMachine = SceneStateMachine()
    private val fade: Transition = Fade()

    private val onLoadMore: (Int) -> Unit = {
        RepoRepository.search(it).subscribe(
                {
                    adapter.addPage(it)
                    if (it.items.isEmpty() && adapter.items.isEmpty()) stateMachine.changeState(EMPTY_STATE)
                    else stateMachine.changeState(LIST_STATE)
                },
                {
                    if (adapter.items.isEmpty()) stateMachine.changeState(ERROR_STATE)
                    else adapter.failPage()
                })
    }

    private val adapter: PaginatingRecyclerAdapter<Repo> = PaginatingRecyclerAdapter<Repo>()
            .loadMore(onLoadMore)
            .register(repoViewType())


    private val onEnterLoading: () -> Unit = {
        onLoadMore.invoke(0)
    }

    private val onEnterList: () -> Unit = {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        adapter.restoreInstanceState(savedInstanceState?.getBundle(STATE_ADAPTER))
        setupStateMachine(savedInstanceState?.getBundle(STATE_MACHINE))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(STATE_MACHINE, stateMachine.saveInstanceState())
        outState.putBundle(STATE_ADAPTER, adapter.saveInstanceState())
    }

    private fun setupStateMachine(savedInstanceState: Bundle?) {
        stateMachine.setup(initalState = LOADING_STATE, restoreState = savedInstanceState) {
            add(LOADING_STATE) {
                scene(R.layout.sc_default_loading to activityRepoListRoot)
                transition(fade)
                onEnter(onEnterLoading)
            }
            add(LIST_STATE) {
                scene(R.layout.sc_default_list to activityRepoListRoot)
                transition(fade)
                onEnter(onEnterList)
            }
            add(EMPTY_STATE) {
                scene(R.layout.sc_repo_list_empty to activityRepoListRoot)
                transition(fade)
            }
            add(ERROR_STATE) {
                scene(R.layout.sc_repo_list_error to activityRepoListRoot)
                transition(fade)
            }
        }
    }
}