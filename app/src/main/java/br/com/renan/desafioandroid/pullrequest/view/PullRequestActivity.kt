package br.com.renan.desafioandroid.pullrequest.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import br.com.renan.desafioandroid.R
import br.com.renan.desafioandroid.model.data.PullRequest
import br.com.renan.desafioandroid.pullrequest.presentation.IPullRequestContract
import br.com.renan.desafioandroid.pullrequest.presentation.PullRequestPresenter
import kotlinx.android.synthetic.main.activity_pull_request.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.toolbar.*

class PullRequestActivity : AppCompatActivity(), IPullRequestContract.View {

    private lateinit var toolbar: Toolbar
    private lateinit var repoName: String
    private lateinit var login: String
    private var open: Int = 0
    private var close: Int = 0
    private val pullRequestPresenter = PullRequestPresenter()
    private lateinit var pullRequestAdapter: PullRequestAdapter
    private val listPullRequest = ArrayList<PullRequest>()

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        toolbar = findViewById(R.id.include_toolbar)

        pullRequestPresenter.bind(this)

        val (login, repoName) = getExtras()

        init(login, repoName)

        setupToolbar(toolbar)

        initListeners(login, repoName)

    }

    private fun setupToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left)
        toolbar.setNavigationOnClickListener(View.OnClickListener() {
            onBackPressed()
        })
        toolbar_title.text = repoName
    }

    private fun initListeners(login: String, creator: String) {
        ivError.setOnClickListener {
            init(login, creator)
        }
    }

    private fun init(login: String, repoName: String) {
        pullRequestPresenter.requestPullRequestData(login, repoName)
    }

    private fun getExtras(): Pair<String, String> {
        login = intent.getStringExtra("creator")
        repoName = intent.getStringExtra("repository")
        return Pair(login, repoName)
    }


    override fun pullRequestSuccess(pullRequestList: List<PullRequest>) {
        listPullRequest.addAll(pullRequestList)
        pullRequestAdapter = PullRequestAdapter(listPullRequest)
        pullRequestRecycler.itemAnimator = DefaultItemAnimator()
        linearLayoutManager = LinearLayoutManager(this)
        pullRequestRecycler.layoutManager = linearLayoutManager
        pullRequestRecycler.adapter = pullRequestAdapter
        pullRequestAdapter.notifyDataSetChanged()
    }

    override fun showTotalPulls(pulls: List<PullRequest>) {
        for (pull in pulls) {
            if (pull.state == "open")
                open++
            else if (pull.state == "close")
                close++
        }
        tvPullRequestAvatar.text = getString(R.string.open_close_pulls, open, close)
    }

    override fun showPullRequestLoading() {
        pbPullRequest.visibility = View.VISIBLE
        include_error_pull_request.visibility = View.GONE
    }

    override fun showPullRequestError() {
        include_error_pull_request.visibility = View.VISIBLE
        pbPullRequest.visibility = View.GONE
        tvPullRequestAvatar.visibility = View.GONE
    }

    override fun showPullRequestSucess() {
        tvPullRequestAvatar.visibility = View.VISIBLE
        pullRequestRecycler.visibility = View.VISIBLE
        include_empty_layout.visibility = View.GONE
        pbPullRequest.visibility = View.GONE
        include_error_pull_request.visibility = View.GONE
    }

    override fun showPullRequestEmpty() {
        include_empty_layout.visibility = View.VISIBLE
        pbPullRequest.visibility = View.GONE
        tvPullRequestAvatar.visibility = View.GONE
        include_error_pull_request.visibility = View.GONE
    }
}
