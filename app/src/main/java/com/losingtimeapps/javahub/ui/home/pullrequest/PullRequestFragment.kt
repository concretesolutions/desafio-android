package com.losingtimeapps.javahub.ui.home.pullrequest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.losingtimeapps.javahub.R
import com.losingtimeapps.javahub.common.presentation.BaseInjectingActivity
import com.losingtimeapps.javahub.common.presentation.BaseGitHubFragment
import com.losingtimeapps.javahub.ui.utils.EndlessScrollListener
import com.losingtimeapps.javahub.utils.Constants
import com.losingtimeapps.presentation.model.PullRequestModel
import com.losingtimeapps.presentation.ui.home.pullrequest.PullRequestView
import com.losingtimeapps.presentation.ui.home.pullrequest.PullRequestViewModel
import com.losingtimeapps.presentation.ui.home.pullrequest.PullRequestViewModelFactory
import kotlinx.android.synthetic.main.fragment_repository.*
import javax.inject.Inject

class PullRequestFragment : BaseGitHubFragment(), PullRequestView {


    lateinit var pullRequestListAdapter: PullRequestListAdapter
    @Inject
    lateinit var pullrequesViewModelFactory: PullRequestViewModelFactory


    companion object {
        fun newInstance(ownerName: String, repoName: String) = PullRequestFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.OWNER_NAME_KEY, ownerName)
                putString(Constants.REPO_NAME_KEY, repoName)
            }
        }
    }

    override fun onInject() {
        val activity = activity as BaseInjectingActivity<*>
        val componentCreator: PullRequestComponent.PullRequestComponentCreator? =
            activity.component as PullRequestComponent.PullRequestComponentCreator
        componentCreator!!.pullRequestComponent().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this, pullrequesViewModelFactory)
            .get(PullRequestViewModel::class.java)
        pullRequestListAdapter = PullRequestListAdapter(viewModel)
        configureRecyclerView(viewModel)
        configureSwipeRefresh(viewModel)
        viewModel.setView(
            this, arguments!!.getString(Constants.OWNER_NAME_KEY, ""),
            arguments!!.getString(Constants.REPO_NAME_KEY, "")
        )
        viewModel.getPullRequests()
    }

    override fun updateOpenClosedNumber(open: Int, closed: Int) {
        ll_count_container.visibility = View.VISIBLE
        tv_open_count.text = " $open " + getString(R.string.opened)
        tv_closet_coount.text = " $closed " + getString(R.string.closed)
    }


    private fun configureRecyclerView(viewModel: PullRequestViewModel) {
        val llm = LinearLayoutManager(activity)
        rv_repositorys.layoutManager = llm
        rv_repositorys.adapter = pullRequestListAdapter
        rv_repositorys?.addOnScrollListener(initRecyclerViewScroll(viewModel, llm))

    }


    private fun initRecyclerViewScroll(
        viewModel: PullRequestViewModel,
        linearLayoutManager: LinearLayoutManager
    ): EndlessScrollListener {
        return object : EndlessScrollListener(linearLayoutManager) {
            override fun onLoadMore(current_page: Int) {
                if (!swipe_container.isRefreshing) {
                    viewModel.getPullRequests(current_page, false)
                }
            }
        }
    }

    fun configureSwipeRefresh(viewModel: PullRequestViewModel) {
        swipe_container.setOnRefreshListener {
            pullRequestListAdapter.resetListAdapter()
            configureRecyclerView(viewModel)
            swipe_container.isRefreshing = false
            viewModel.getPullRequests(1, true)

        }
    }

    override fun updateRepoLiveData(pullRequestListData: List<PullRequestModel>) {
        pullRequestListAdapter.updateData(pullRequestListData)
    }

    override fun navigateToPullRequestView(pullRequestUrl: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW, Uri.parse(pullRequestUrl)
            )
        )
    }


}