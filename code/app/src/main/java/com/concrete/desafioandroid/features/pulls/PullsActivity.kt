package com.concrete.desafioandroid.features.pulls

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.concrete.desafioandroid.R
import com.concrete.desafioandroid.data.models.PullRequest
import com.concrete.desafioandroid.features.base.BaseActivity
import com.concrete.desafioandroid.features.pulls.adapter.PullsAdapter
import com.concrete.desafioandroid.utils.*
import kotlinx.android.synthetic.main.activity_pulls.*
import kotlinx.android.synthetic.main.component_empty_view.*
import org.kodein.di.Kodein
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class PullsActivity: BaseActivity<PullsView>(), PullsView {

    override val kodein: Kodein by closestKodein()

    override val layoutId: Int = R.layout.activity_pulls
    override val presenter: PullsPresenter by instance()

    private lateinit var adapter: PullsAdapter
    private val pullsList = ArrayList<PullRequest>()
    private var openedIssues: Int = 0
    private var closedIssues: Int = 0

    override fun setPresenter() {
        presenter.attachView(this)
    }

    override fun onCreate() {
        title = intent.getStringExtra(INTENT_EXTRA_PULL_TITLE)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        emptyTextView?.text = getString(R.string.empty_pull_requests_text)

        pullsRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        pullsRecyclerView.setHasFixedSize(true)
        adapter = PullsAdapter(pullsList, applicationContext
        ) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(it.htmlUrl))
            startActivity(intent);
        }
        pullsRecyclerView.adapter = adapter

        presenter.getPullsRequests(intent.getStringExtra(INTENT_EXTRA_PULL_URL), !hasSavedInstances)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelableArrayList(EXTRA_LIST, pullsList)
        outState?.putInt(EXTRA_OPENED_ISSUES_TEXT, openedIssues)
        outState?.putInt(EXTRA_CLOSED_ISSUES_TEXT, closedIssues)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        reloadList(savedInstanceState?.getParcelableArrayList<PullRequest>(EXTRA_LIST)!!)
        updateUi(savedInstanceState.getInt(EXTRA_OPENED_ISSUES_TEXT),
                savedInstanceState.getInt(EXTRA_CLOSED_ISSUES_TEXT))
    }

    override fun onGetPullsRequests(pulls: List<PullRequest>) {
        reloadList(pulls)
    }

    override fun updateUi(opened: Int, closed: Int) {
        openedIssues = opened
        closedIssues = closed

        pullsOpenedTextView.text = getString(R.string.pull_requests_header_opened_text, openedIssues)
        pullsClosedTextView.text = getString(R.string.pull_requests_header_closed_text, closedIssues)
    }

    override fun showError(messsage: String?) {
        showMessage(messsage)
        showEmptyView(true)
    }

    private fun reloadList(list: List<PullRequest>) {
        showEmptyView(list.isEmpty())
        pullsList.addAll(list)
        adapter.notifyDataSetChanged()
    }

    private fun showEmptyView(isEmpty: Boolean) {
        if (isEmpty) {
            emptyContainerView.visibility = View.VISIBLE

        } else {
            emptyContainerView.visibility = View.GONE
        }
    }

}