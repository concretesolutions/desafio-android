package br.com.concrete.desafio.feature.pullrequest

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.transition.Fade
import android.transition.Transition
import br.com.concrete.desafio.*
import br.com.concrete.desafio.adapter.BaseRecyclerAdapter
import br.com.concrete.desafio.feature.BaseActivity
import br.com.concrete.desafio.statemachine.SceneStateMachine
import br.com.concrete.sdk.handler.observe
import br.com.concrete.sdk.model.PullRequest
import br.com.concrete.sdk.model.Repo
import br.com.concrete.sdk.model.type.ERROR
import br.com.concrete.sdk.model.type.LOADING
import br.com.concrete.sdk.model.type.SUCCESS
import kotlinx.android.synthetic.main.activity_pull_request_list.*
import kotlinx.android.synthetic.main.sc_default_list.*

class PullRequestListActivity : BaseActivity() {

    companion object Statics {
        private const val EXTRA_REPO = "EXTRA_REPO"

        fun intent(context: Context, repo: Repo): Intent {
            return Intent(context, PullRequestListActivity::class.java).putExtra(EXTRA_REPO, repo)
        }
    }

    private val stateMachine = SceneStateMachine()
    private val fade: Transition = Fade()
    private val adapter: BaseRecyclerAdapter<PullRequest> = BaseRecyclerAdapter<PullRequest>().register(pullRequestViewType())
    private val viewModel by lazy {
        val viewModel = ViewModelProviders.of(this)[PullRequestListViewModel::class.java]
        viewModel.repo = intent.extras.getParcelable<Repo>(EXTRA_REPO)
        viewModel
    }

    private val onEnterList: () -> Unit = {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_list)
        viewModel.repo?.let { toolbar?.title = it.name.capitalize() }

        setupStateMachine()

        viewModel.listPullRequest.observe(this) {
            adapter.setList(it.data ?: emptyList())
            when (it.status) {
                SUCCESS -> stateMachine.changeState(LIST_STATE)
                LOADING -> stateMachine.changeState(if (adapter.items.isEmpty()) LOADING_STATE else LIST_STATE)
                ERROR -> stateMachine.changeState(ERROR_STATE)
            }
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