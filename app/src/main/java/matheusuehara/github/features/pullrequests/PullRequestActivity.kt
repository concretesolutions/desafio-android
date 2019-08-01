package matheusuehara.github.features.pullrequests

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_INDEFINITE
import kotlinx.android.synthetic.main.activity_pull_request.*
import matheusuehara.github.R
import matheusuehara.github.data.model.PullRequest
import matheusuehara.github.data.model.ViewStateModel
import matheusuehara.github.util.Constants.INTENT_REPOSITORY_NAME
import matheusuehara.github.util.Constants.INTENT_REPOSITORY_OWNER_NAME
import matheusuehara.github.util.Constants.PULL_REQUEST_STATE_CLOSED
import matheusuehara.github.util.Constants.PULL_REQUEST_STATE_OPEN
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class PullRequestActivity : AppCompatActivity(), PullRequestClickListener {

    private var adapter: PullRequestAdapter = PullRequestAdapter(ArrayList(), this)
    private val pullRequestViewModel: PullRequestViewModel by viewModel()
    private lateinit var layoutManager: LinearLayoutManager

    private var repositoryName: String = String()
    private var repositoryOwnerName: String = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        intent.getStringExtra(INTENT_REPOSITORY_NAME)?.let {
            repositoryName = it
        }

        intent.getStringExtra(INTENT_REPOSITORY_OWNER_NAME)?.let {
            repositoryOwnerName = it
        }

        supportActionBar?.title = repositoryName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initPullRequests()
        initObservable()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initPullRequests() {
        layoutManager = LinearLayoutManager(this)
        rvPullRequest.layoutManager = layoutManager
        rvPullRequest.adapter = adapter
        rvPullRequest.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))

    }

    private fun initObservable() {
        getPullRequests()
        this.lifecycle.addObserver(pullRequestViewModel)
        pullRequestViewModel.getPullRequests().observe(this, Observer { stateModel ->
            when (stateModel.status) {
                ViewStateModel.Status.LOADING -> {
                    progress_bar.visibility = VISIBLE
                }
                ViewStateModel.Status.SUCCESS -> {
                    progress_bar.visibility = GONE
                    stateModel.model?.let { pullrequests ->
                        if (pullrequests.isEmpty()) showEmptyPullRequestMessage()
                        else {
                            adapter.addPullRequests(pullrequests)
                            val closedPr = pullrequests.count { it.state == PULL_REQUEST_STATE_CLOSED}
                            val openPr = pullrequests.count { it.state == PULL_REQUEST_STATE_OPEN }
                            status.text = getString(R.string.status, openPr, closedPr)
                        }
                    }
                }
                ViewStateModel.Status.ERROR -> {
                    progress_bar.visibility = GONE
                    showNetworkError()
                }
            }
        })
    }

    private fun getPullRequests() {
        pullRequestViewModel.loadPullRequests(repositoryOwnerName, repositoryName)
    }

    private fun showNetworkError() {
        Snackbar.make(
                frame_layout,
                R.string.connection_error,
                LENGTH_INDEFINITE)
                .setAction(R.string.try_again) {
                    getPullRequests()
                }.show()
    }

    private fun showEmptyPullRequestMessage() {
        Snackbar.make(frame_layout,
                R.string.empty_result,
                LENGTH_INDEFINITE).show()
    }

    override fun onClick(pullRequest: PullRequest) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(pullRequest.html_url)
        startActivity(i)
    }

}
