package br.com.concrete.desafio.feature.repo

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.transition.Fade
import android.transition.Transition
import br.com.concrete.desafio.*
import br.com.concrete.desafio.adapter.PaginatingRecyclerAdapter
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

    private val onLoadMore: (Int) -> Unit = {
        viewModel.search(this, it,
                success = adapter::addPage,
                error = { adapter.failPage() })
    }

    private val adapter: PaginatingRecyclerAdapter<Repo> = PaginatingRecyclerAdapter<Repo>().loadMore(onLoadMore).register(repoViewType())

    private val onEnterList = {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        setupStateMachine()

        viewModel.repoList.observeSingleData(this) {
            it?.let {
                adapter.addPage(it)
                stateMachine.changeState(if (adapter.items.isEmpty()) EMPTY_STATE else LIST_STATE)
            } ?: stateMachine.changeState(LOADING_STATE)
        }

        viewModel.repoList.observeSingleError(this) { stateMachine.changeState(ERROR_STATE) }
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