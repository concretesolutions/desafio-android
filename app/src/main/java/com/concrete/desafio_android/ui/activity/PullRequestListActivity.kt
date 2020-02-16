package com.concrete.desafio_android.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.concrete.desafio_android.PullRequestsContract
import com.concrete.desafio_android.PullRequestsPresenter
import com.concrete.desafio_android.R
import com.concrete.desafio_android.domain.PullRequest
import com.concrete.desafio_android.domain.Repository
import com.concrete.desafio_android.ui.adapter.PullRequestListAdapter
import kotlinx.android.synthetic.main.activity_pull_request_list.pull_request_list

class PullRequestListActivity: AppCompatActivity(), PullRequestsContract.View {

    private lateinit var repository: Repository
    private val presenter: PullRequestsContract.Presenter = PullRequestsPresenter(this)
    private val pullRequests = ArrayList<PullRequest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_list)
        intent.getParcelableExtra<Repository>("repository")?.let {
            repository = it
            presenter.getPullRequests(repository.owner.login, repository.name)
            setTitle()
            pull_request_list.adapter = PullRequestListAdapter(pullRequests, this){
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url)))
            }
        }
    }

    private fun setTitle() {
        title = repository.name + "'s Pull Requests"
    }

    override fun showList(pullReqs: ArrayList<PullRequest>) {
        pullRequests.addAll(pullReqs)
        pull_request_list.adapter?.notifyDataSetChanged()
    }

    override fun showFailureMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}