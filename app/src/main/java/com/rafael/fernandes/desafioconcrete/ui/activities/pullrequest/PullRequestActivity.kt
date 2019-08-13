package com.rafael.fernandes.desafioconcrete.ui.activities.pullrequest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafael.fernandes.desafioconcrete.R
import com.rafael.fernandes.desafioconcrete.databinding.PullRequestActivityBinding
import com.rafael.fernandes.desafioconcrete.extentions.showToast
import com.rafael.fernandes.desafioconcrete.presentation.resources.Resource
import com.rafael.fernandes.desafioconcrete.ui.adapters.ListPullRequestAdapter
import com.rafael.fernandes.desafioconcrete.ui.base.BaseActivity
import com.rafael.fernandes.domain.model.GitPullRequests
import kotlinx.android.synthetic.main.pull_request_activity.*


class PullRequestActivity : BaseActivity<PullRequestActivityBinding, PullRequestViewModel>() {
    private lateinit var recyclerView: RecyclerView
    private var name: String = ""
    private var repositoryName: String = ""

    private val itemClick: (GitPullRequests) -> Unit =
        {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.htmlUrl))
            startActivity(browserIntent)
        }

    private val adapter = ListPullRequestAdapter(itemClick)

    override fun layoutId(): Int = R.layout.pull_request_activity

    override fun myOnCreate(savedInstanceState: Bundle?) {
        addBackButtonOnToolbar()
        bindObservables()
        setupRecyclerView()
        adapter.submitList(null)
        intent.extras?.run {

            name = this.getString(KEY_NAME)
            repositoryName = this.getString(KEY_REPOSITORY_NAME)
            title = repositoryName

            if (savedInstanceState != null) {
                val list = savedInstanceState.getSerializable(KEY_LIST) as List<GitPullRequests>
                adapter.updateList(list)
                return
            }

            mViewModel.listPullRequests(name, repositoryName)
        }
    }

    private fun bindObservables() {
        mViewModel.mObservableGitPullRequest.observe(this, Observer {
            updateView(it)
        })
    }

    private fun updateView(resource: Resource<List<GitPullRequests>>) {
        onStateChange(resource)

        resource?.let {
            onStateChange(it)
        }
    }

    override fun onLoading() {
        swipe_refresh.isRefreshing = true
    }

    override fun <H> onSuccess(data: H?) {
        swipe_refresh.isRefreshing = false
        val item = data as List<GitPullRequests>
        adapter.updateList(item)

    }

    override fun onError(message: String?) {
        swipe_refresh.isRefreshing = false
        message?.run {
            showToast(this)
        }
    }

    private fun setupRecyclerView() {
        recyclerView = recycler_view
        val layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )

        recyclerView.layoutManager = layoutManager

        swipe_refresh.setOnRefreshListener {
        }

        recyclerView.addItemDecoration(divider)
        recyclerView.adapter = adapter
        adapter.submitList(null)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        adapter.getList()?.run {
            outState.putSerializable(KEY_LIST, this)
        }
    }

    companion object {
        val KEY_NAME = "name"
        val KEY_REPOSITORY_NAME = "repository_name"
        private val KEY_LIST = "list"
    }
}