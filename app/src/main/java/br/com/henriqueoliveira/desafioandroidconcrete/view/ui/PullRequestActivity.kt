package br.com.henriqueoliveira.desafioandroidconcrete.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.henriqueoliveira.desafioandroidconcrete.R
import br.com.henriqueoliveira.desafioandroidconcrete.helpers.toHtmlColored
import br.com.henriqueoliveira.desafioandroidconcrete.helpers.toast
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import br.com.henriqueoliveira.desafioandroidconcrete.service.repository.Resource
import br.com.henriqueoliveira.desafioandroidconcrete.view.adapter.PullRequestsAdapter
import br.com.henriqueoliveira.desafioandroidconcrete.viewmodel.RepositoryListViewModel
import kotlinx.android.synthetic.main.pull_request_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PullRequestActivity : BaseActivity() {

    private var repositoryOwner: String = ""
    private var repositoryName: String = ""
    private val repoViewModel: RepositoryListViewModel by viewModel()
    private lateinit var adapter: PullRequestsAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pull_request_activity)

        setupToolbar(toolbar,R.string.pull_requests, R.drawable.ic_back, true)
        swipeRefreshLayout.isEnabled = false

        repositoryOwner = intent.getStringExtra(EXTRA_REPOSITORY_OWNER)
        repositoryName = intent.getStringExtra(EXTRA_REPOSITORY_NAME)
        if (repositoryOwner.isBlank() || repositoryName.isBlank()) {
            toast(getString(R.string.invalid_repositoryId))
            finish()
            return
        }

        supportStartPostponedEnterTransition()

        handleViewModel(repositoryOwner, repositoryName)
        configRecycler()

    }

    private fun configRecycler() {
        adapter = PullRequestsAdapter(arrayListOf()) { pullrequest -> onPRItemClick(pullrequest) }
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerPR.layoutManager = layoutManager
        recyclerPR.adapter = adapter
    }

    private fun handleViewModel(repositoryOwner: String, repositoryName: String) {
        repoViewModel.loadPullRequests(repositoryOwner, repositoryName)

        repoViewModel.isLoading.observe(this, Observer {
            it?.let { swipeRefreshLayout.isRefreshing = it }
        })

        repoViewModel.statePR.observe(this, Observer {
            when (it.status) {

                Resource.RequestStatus.SUCCESS -> {
                    it.data?.let { adapter.updateList(it) }
                    it.data?.let {
                        setupCounter(it)
                    }

                }
                Resource.RequestStatus.ERROR -> {
                    it.data?.let { adapter.updateList(it) }
                    it.message?.let {
                        toast(it)
                        finish()}

                }
                Resource.RequestStatus.LOADING -> swipeRefreshLayout.isRefreshing = true
            }
        })
    }

    private fun setupCounter(pullRequests: List<PullRequest>) {
        val opened = pullRequests.count { status -> status.state == "open" }
        val closed = pullRequests.count { status -> status.state == "close" }

        val statusOpen = "$opened opened".toHtmlColored("#E29132")
        val statusClose = " / $closed closed".toHtmlColored("#000000")

        val spanned: Spanned
        spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(statusOpen + statusClose, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(statusOpen + statusClose)
        }
        status.text = spanned
    }

    private fun onPRItemClick(pr: PullRequest) {
        val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pr.url))
        startActivity(myIntent)
    }


    companion object {

        const val EXTRA_REPOSITORY_NAME = "EXTRA_REPOSITORY_NAME"
        const val EXTRA_REPOSITORY_OWNER = "EXTRA_REPOSITORY_OWNER"


    }
}