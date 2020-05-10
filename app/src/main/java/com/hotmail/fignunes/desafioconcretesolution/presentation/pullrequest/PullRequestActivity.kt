package com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.hotmail.fignunes.desafioconcretesolution.R
import com.hotmail.fignunes.desafioconcretesolution.common.BaseActivity
import com.hotmail.fignunes.desafioconcretesolution.databinding.ActivityPullRequestBinding
import com.hotmail.fignunes.desafioconcretesolution.model.PullRequest
import com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.GitRepositoryActivity
import com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.adapter.PullRequestAdapter
import com.hotmail.fignunes.skytestefabionunes.model.GitRepositoryItem
import kotlinx.android.synthetic.main.activity_pull_request.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PullRequestActivity : BaseActivity(), PullRequestContract, PullRequestAdapter.ClickPullRequest,
    PullRequestAdapter.SetPosition {

    private var positionPullRequest: Int = 0
    private lateinit var adapter: PullRequestAdapter
    private lateinit var pullRequests: MutableList<PullRequest>
    private val POSITION = "position"
    private val PULL_REQUESTS = "pull_requests"

    private val presenter: PullRequestPresenter by inject { parametersOf(this) }
    private lateinit var binding: ActivityPullRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pull_request)
        binding.presenter = presenter
        backButton()
        val gitRepositoryItem: GitRepositoryItem? = intent.getParcelableExtra(GitRepositoryActivity.GIT_REPOSITORY_ITEM)
        title = gitRepositoryItem!!.name

        if (savedInstanceState == null) {
            presenter.onCreate(gitRepositoryItem)
        } else {
            pullRequests = savedInstanceState.getParcelableArrayList<PullRequest>(PULL_REQUESTS)!!.toMutableList()
            positionPullRequest = savedInstanceState.getInt(POSITION)
            initializeAdapter(pullRequests)
            presenter.message(pullRequests.toList())
        }
    }

    override fun initializeSwipe() {
        pullRequestSwipe.setOnRefreshListener {
            positionPullRequest = 0
            presenter.searchPullRequest()
        }
    }

    override fun initializeAdapter(pullRequests: List<PullRequest>) {
        this.pullRequests = pullRequests as MutableList
        adapter = PullRequestAdapter(this, pullRequests, this, this)
        pullRequestRecyclerview.adapter = adapter
        positionList()
    }

    override fun swipeOff() {
        pullRequestSwipe.isRefreshing = false
    }

    private fun positionList() {
        pullRequestRecyclerview.layoutManager!!.scrollToPosition(positionPullRequest)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun click(pullRequest: PullRequest) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pullRequest.html_url))
        startActivity(intent)
    }

    override fun set(position: Int) {
        positionPullRequest = position
    }

    override fun message(error: Int) {
        toast(error)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(PULL_REQUESTS, ArrayList(pullRequests))
        outState.putInt(POSITION, positionPullRequest)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}
