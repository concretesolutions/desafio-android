package com.concrete.desafio_android.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.concrete.desafio_android.contract.PullRequestsContract
import com.concrete.desafio_android.presenter.PullRequestsPresenter
import com.concrete.desafio_android.R
import com.concrete.desafio_android.domain.PullRequest
import com.concrete.desafio_android.domain.Repository
import com.concrete.desafio_android.ui.adapter.PullRequestListAdapter
import com.concrete.desafio_android.util.REPOSITORY_TAG
import kotlinx.android.synthetic.main.activity_pull_request_list.pull_request_list

class PullRequestListActivity : AppCompatActivity(), PullRequestsContract.View {

    private lateinit var repository: Repository
    private val presenter: PullRequestsContract.Presenter =
        PullRequestsPresenter(this)
    private val pullRequests = ArrayList<PullRequest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_list)
        intent.getParcelableExtra<Repository>(REPOSITORY_TAG)?.let {
            repository = it
            presenter.getPullRequests(repository.owner.login, repository.name)
            setTitle()
            setRequestsList()
            addListDivider()
        } ?: noRepositoryError()
    }

    private fun addListDivider() {
        val dividerItemDecoration = DividerItemDecoration(
            pull_request_list.context,
            DividerItemDecoration.VERTICAL
        )
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.list_item_divider)!!)
        pull_request_list.addItemDecoration(dividerItemDecoration)
    }

    private fun setRequestsList() {
        pull_request_list.adapter = PullRequestListAdapter(pullRequests, this) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url)))
        }
    }

    private fun noRepositoryError() {
        showErrorMessage(R.string.repository_not_loaded_error)
        finish()
    }

    private fun setTitle() {
        title = presenter.makeTitle(repository.name)
    }

    override fun showList(pullReqs: ArrayList<PullRequest>) {
        pullRequests.addAll(pullReqs)
        pull_request_list.adapter?.notifyDataSetChanged()
    }

    override fun showErrorMessage(messageId: Int) {
        val message = resources.getString(messageId)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}