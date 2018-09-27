package br.com.concrete.desafio.feature.repo

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.transition.Fade
import android.transition.Transition
import android.view.ViewGroup
import br.com.arch.toolkit.recycler.adapter.SimpleAdapter
import br.com.arch.toolkit.statemachine.SceneStateMachine
import br.com.arch.toolkit.statemachine.config
import br.com.arch.toolkit.statemachine.scene
import br.com.arch.toolkit.statemachine.setup
import br.com.arch.toolkit.statemachine.state
import br.com.concrete.desafio.base.BaseActivity
import br.com.concrete.desafio.base.extension.addStatusBarPadding
import br.com.concrete.desafio.base.intentPullRequestList
import br.com.concrete.desafio.base.viewModelProvider
import br.com.concrete.desafio.data.model.dto.RepoDTO
import br.com.concrete.desafio.feature.repo.item.RepoItemView

private const val LIST_STATE = 0
private const val LOADING_STATE = 1
private const val EMPTY_STATE = 2
private const val ERROR_STATE = 3

class RepoListActivity : BaseActivity() {

    private val viewModel by viewModelProvider(RepoListViewModel::class)

    private val stateMachine = SceneStateMachine()
    private val fade: Transition = Fade()

//    private val onLoadMore: (Int) -> Unit = { page ->
//        viewModel.search(this, page,
//                success = adapter::addPage,
//                error = { adapter.failPage() })
//    }

    private val adapter = SimpleAdapter(::RepoItemView).withListener(::onRepoItemClick)

    private lateinit var content: ViewGroup
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView

    private val onEnterList = {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        content = findViewById(R.id.content)
        toolbar = findViewById(R.id.toolbar)

        toolbar.addStatusBarPadding()
        setSupportActionBar(toolbar)

        setupStateMachine()

        viewModel.repoList.observeSingleData(this) {
            it?.run {
                adapter.setList(this.items)
                stateMachine.changeState(if (adapter.itemCount == 0) EMPTY_STATE else LIST_STATE)
            } ?: stateMachine.changeState(LOADING_STATE)
        }

        viewModel.repoList.observeSingleError(this) { stateMachine.changeState(ERROR_STATE) }
    }

    private fun setupStateMachine() {
        stateMachine.setup {
            state(LOADING_STATE) {
                scene(R.layout.sc_default_loading to content)
                transition(fade)
            }
            state(LIST_STATE) {
                scene(R.layout.sc_default_list to content)
                transition(fade)
                onEnter(onEnterList)
            }
            state(EMPTY_STATE) {
                scene(R.layout.sc_repo_list_empty to content)
                transition(fade)
            }
            state(ERROR_STATE) {
                scene(R.layout.sc_repo_list_error to content)
                transition(fade)
            }
            config {
                initialState = LOADING_STATE
            }
        }
    }

    private fun onRepoItemClick(repo: RepoDTO) {
        startActivity(intentPullRequestList(repo))
    }
}