package br.com.concrete.desafio.feature.repo

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.transition.Fade
import android.transition.Transition
import br.com.concrete.desafio.*
import br.com.concrete.desafio.adapter.BaseRecyclerAdapter
import br.com.concrete.desafio.feature.BaseActivity
import br.com.concrete.desafio.feature.viewModelProvider
import br.com.concrete.desafio.statemachine.SceneStateMachine
import br.com.concrete.sdk.model.Repo
import kotlinx.android.synthetic.main.activity_repo_list.*
import kotlinx.android.synthetic.main.sc_default_list.*

class RepoListActivity : BaseActivity() {

    private val viewModel by viewModelProvider(RepoListViewModel::class)

    private val stateMachine = SceneStateMachine()
    private val fade: Transition = Fade()

    private val adapter: BaseRecyclerAdapter<Repo> = BaseRecyclerAdapter<Repo>()
            .register(repoViewType())

    private val onEnterList: () -> Unit = {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        setupStateMachine()

        viewModel.repoList.observeData(this) {
            it?.let {
                it.items.let(adapter::setList)
                stateMachine.changeState(if (adapter.items.isEmpty()) EMPTY_STATE else LIST_STATE)
            } ?: stateMachine.changeState(LOADING_STATE)
        }

        viewModel.repoList.observeError(this) {
            stateMachine.changeState(ERROR_STATE)
        }
    }

    private fun setupStateMachine() {
        stateMachine.setup {
            add(LOADING_STATE) {
                scene(R.layout.sc_default_loading to content)
                transition(fade)
            }
            add(LIST_STATE) {
                scene(R.layout.sc_default_list to content)
                transition(fade)
                onEnter(onEnterList)
            }
            add(EMPTY_STATE) {
                scene(R.layout.sc_repo_list_empty to content)
                transition(fade)
            }
            add(ERROR_STATE) {
                scene(R.layout.sc_repo_list_error to content)
                transition(fade)
            }
        }
    }
}