package matheusuehara.github.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_INDEFINITE
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_pull_request.*
import matheusuehara.github.R
import matheusuehara.github.contract.PullRequestContract
import matheusuehara.github.model.PullRequest
import matheusuehara.github.view.adapters.PullRequestAdapter
import matheusuehara.github.presenter.PullRequestPresenterImpl
import java.util.ArrayList

class PullRequestActivity : AppCompatActivity(), PullRequestContract.View{

    private var adapter: PullRequestAdapter = PullRequestAdapter(ArrayList(), this)
    private var presenter:PullRequestContract.Presenter = PullRequestPresenterImpl(this)

    private var repositoryName: String? = null
    private var repositoryOwnerName: String? = null

    companion object {
        val STATUS:String = "all"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        repositoryName = intent.getStringExtra("repositoryName")
        repositoryOwnerName = intent.getStringExtra("repositoryOwnerName")

        presenter.getPullRequests(repositoryOwnerName!!,repositoryName!!,STATUS)

        supportActionBar?.setTitle(repositoryName)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        rvPullRequest.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this)
        rvPullRequest.layoutManager = linearLayoutManager
        rvPullRequest.addItemDecoration(DividerItemDecoration(this, linearLayoutManager!!.orientation))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updatePullRequests(pullRequestResult: ArrayList<PullRequest>) {
        adapter!!.pullRequests = pullRequestResult
        adapter!!.notifyDataSetChanged()
    }

    override fun showProgressBar(){
        progress_bar.visibility = VISIBLE
    }

    override fun hideProgressBar(){
        progress_bar.visibility = GONE
    }

    override fun showNetworkError(){
        Snackbar.make(
                frame_layout,
                R.string.connection_error,
                LENGTH_INDEFINITE)
                .setAction(R.string.try_again
                ) {presenter.getPullRequests(repositoryOwnerName!!,repositoryName!!,STATUS)}.show()
    }

    override fun showEmptyPullRequestMessage() {
        Snackbar.make(frame_layout,
                R.string.empty_result,
                LENGTH_INDEFINITE).show()
    }

    override fun updateStatus(statusValue: String) {
        status.text = statusValue
    }
}
