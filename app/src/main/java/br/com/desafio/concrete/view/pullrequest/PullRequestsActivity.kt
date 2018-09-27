package br.com.desafio.concrete.view.pullrequest

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.text.Spanned
import android.view.View
import br.com.desafio.concrete.R
import br.com.desafio.concrete.base.BaseActivity
import br.com.desafio.concrete.extension.finishActivity
import br.com.desafio.concrete.extension.toHtmlColored
import br.com.desafio.concrete.model.PullRequest
import br.com.desafio.concrete.model.Repository
import br.com.desafio.concrete.view.pullrequest.adapter.PullRequestAdapter
import kotlinx.android.synthetic.main.pullrequest_activity.*
import kotlinx.android.synthetic.main.toolbar_include.*
import org.koin.android.ext.android.inject
import java.util.*

/**
 * Created by Malkes on 25/09/2018.
 */
class PullRequestsActivity : BaseActivity(), PullRequestContract.View {

    companion object {
        const val REPOSITORY_EXTRA = "repositoryExtra"
        const val PULL_REQUESTS_EXTRA = "pullRequestsExtra"
        const val RECYCLER_STATE_EXTRA = "recyclerState"
    }

    private val presenter by inject<PullRequestContract.Presenter> ()
    private var pullRequests: ArrayList<PullRequest>? = null
    private var repository: Repository? = null
    private var recyclerState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pullrequest_activity)

        setupToolbar(toolbar,R.string.pull_requests_toolbar, R.drawable.ic_back, true)

        presenter.attachView(this)
        swipeRefreshLayout.isEnabled = false

        repository = intent.extras.getParcelable(REPOSITORY_EXTRA)

        fetchPullRequests(savedInstanceState)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun fetchPullRequests(savedInstanceState: Bundle?) {

        if(savedInstanceState == null){
            fetchFromApi()
        }else{
            val list = savedInstanceState.getParcelableArrayList<PullRequest>(PULL_REQUESTS_EXTRA)
            recyclerState = savedInstanceState.getParcelable<Parcelable>(RECYCLER_STATE_EXTRA)
            if(list != null && list.isNotEmpty()){
                onPullRequestLoaded(list)
            }else{
                fetchFromApi()
            }
        }
    }

    private fun fetchFromApi(){
        repository?.let{
            presenter.fetchPullRequests(it)
        }
    }

    override fun onListItemClicked(pullRequest: PullRequest) {
        val builder = CustomTabsIntent.Builder()
        builder.setStartAnimations(this, R.anim.translate_slide_up, R.anim.translate_no_change)
        builder.setExitAnimations(this, R.anim.translate_no_change, R.anim.translate_slide_down)
        val customTabs = builder.build()
        customTabs.launchUrl(this, Uri.parse(pullRequest.url))
    }

    override fun onPullRequestLoaded(pullRequests: ArrayList<PullRequest>) {
        this.pullRequests = pullRequests
        setupRecyclerView(pullRequests)
        setupCounter(pullRequests)
    }

    private fun setupCounter(pullRequests: List<PullRequest>) {
        val opened = pullRequests.count { status-> status.state == "open"}
        val closed = pullRequests.count { status-> status.state == "close"}

        val statusOpen = "$opened opened".toHtmlColored("#E29132")
        val statusClose = " / $closed closed".toHtmlColored("#000000")

        val spanned : Spanned
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spanned = Html.fromHtml(statusOpen + statusClose, Html.FROM_HTML_MODE_LEGACY)
        }else{
            spanned = Html.fromHtml(statusOpen + statusClose)
        }
        tvStatus.text = spanned
    }

    private fun setupRecyclerView(pullRequests: List<PullRequest>) {
        val layoutManager = LinearLayoutManager(this)
        recyclerState?.let {
            layoutManager.onRestoreInstanceState(it)
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        recyclerView.adapter = PullRequestAdapter(pullRequests, this)
    }

    override fun showLoadingIndicator(loadingVisible: Boolean) {
        swipeRefreshLayout.isRefreshing = loadingVisible
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishActivity()
    }

    override fun showEmptyState() {
        tvStatus.visibility = View.GONE
        noPullInclude.visibility = View.VISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        pullRequests?.let {
            outState?.let{ bundle ->
                bundle.putParcelableArrayList(PULL_REQUESTS_EXTRA, it)
                bundle.putParcelable(RECYCLER_STATE_EXTRA, recyclerView.layoutManager.onSaveInstanceState())
            }
        }
    }
}