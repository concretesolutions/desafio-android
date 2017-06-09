package br.com.concrete.desafio.feature.pullrequest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.transition.Fade
import android.transition.Transition
import br.com.concrete.desafio.*
import br.com.concrete.desafio.adapter.BaseRecyclerAdapter
import br.com.concrete.desafio.decorator.DividerItemDecoration
import br.com.concrete.desafio.feature.BaseActivity
import br.com.concrete.desafio.statemachine.SceneStateMachine
import br.com.concrete.desafio.util.enableBack
import br.com.concrete.sdk.PullRequestRepository
import br.com.concrete.sdk.model.PullRequest
import br.com.concrete.sdk.model.Repo
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
    private val repo: Repo by lazy { intent.extras.getParcelable<Repo>(EXTRA_REPO) }

    private val onEnterLoading: () -> Unit = {
        PullRequestRepository.list(repo).subscribe(
                {
                    adapter.addAll(it)
                    if (it.isEmpty() && adapter.items.isEmpty()) stateMachine.changeState(EMPTY_STATE)
                    else stateMachine.changeState(LIST_STATE)
                },
                { stateMachine.changeState(ERROR_STATE) })
    }

    private val onEnterList: () -> Unit = {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, R.dimen.default_margin))
        recyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_list)
        supportActionBar.enableBack()
        supportActionBar?.title = repo.name.capitalize()
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
                scene(R.layout.sc_default_loading to content)
                transition(fade)
                onEnter(onEnterLoading)
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