package br.com.cavreti.desafio_android.useCase.repositoryPullRequests

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cavreti.desafio_android.R
import br.com.cavreti.desafio_android.applicationCore.CustomApplication
import br.com.cavreti.desafio_android.applicationCore.base.BaseActivity
import br.com.cavreti.desafio_android.data.PullRequest
import br.com.cavreti.desafio_android.data.Repository
import br.com.cavreti.desafio_android.util.OnAdapterItemClickListener
import kotlinx.android.synthetic.main.activity_home.recyclerView
import kotlinx.android.synthetic.main.activity_pull_requests.*
import javax.inject.Inject
import android.widget.Toast
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView


import android.view.MenuItem


class PullRequestsActivity : BaseActivity(), PullRequestsContract.View, OnAdapterItemClickListener {

    @Inject
    lateinit var presenter: PullRequestsPresenter

    lateinit var repository: Repository

    private lateinit var adapter: PullRequestListAdapter

    private var pullRequests: MutableList<PullRequest> = arrayListOf()

    private val firstListPage: Int = 1
    var currentListPage: Int = firstListPage
    var isLastPage: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)


        DaggerPullRequestsComponent.builder()
            .serviceComponent((applicationContext as CustomApplication).getServiceComponent())
            .pullRequestsModule(PullRequestsModule(this))
            .build()
            .inject(this)

        loadAdapter(arrayListOf())
        repository = intent.extras.get("repository") as Repository

        setupToolbar(toolbar, repository.name)

        presenter.getPullRequests(repository.owner.name,repository.name, currentListPage)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.back_enter, R.anim.back_exit)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadAdapter(pullRequests: List<PullRequest>) {
        adapter = PullRequestListAdapter(pullRequests, this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItem = manager.findLastCompletelyVisibleItemPosition()

                if(lastVisibleItem == pullRequests.size - 1 && !isLastPage ) {
                    currentListPage++
                    progressBar.visibility = View.VISIBLE
                    presenter.getPullRequests(repository.owner.name, repository.name, currentListPage)
                }
            }
        })
    }

    override fun loadPullRequests(pullRequests: List<PullRequest>) {
        progressBar.visibility = View.GONE

        if(pullRequests.isEmpty()){
            isLastPage = true
            return
        }

        this.pullRequests.addAll(pullRequests)
        if(currentListPage == firstListPage) {
            loadAdapter(this.pullRequests)
        }else {
            adapter.notifyItemInserted(this.pullRequests.size - 1)
        }

    }

    override fun loadPullRequestsFailed() {
        progressBar.visibility = View.GONE
        tryAgainLayout.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun onItemClick(item: Any, position: Int) {
        val pullRequest = item as PullRequest
        openUrl(pullRequest.url ?: "")
    }

    private fun openUrl(url: String) {
        try {
            val webPage = Uri.parse(url)
            val myIntent = Intent(Intent.ACTION_VIEW, webPage)
            startActivity(myIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                this,
                getString(R.string.webpage_intent_error_message),
                Toast.LENGTH_LONG
            ).show()
            e.printStackTrace()
        }
    }

    fun tryAgain(view : View) {
        presenter.getPullRequests(repository.owner.name,repository.name, currentListPage)
        progressBar.visibility = View.VISIBLE
        tryAgainLayout.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}