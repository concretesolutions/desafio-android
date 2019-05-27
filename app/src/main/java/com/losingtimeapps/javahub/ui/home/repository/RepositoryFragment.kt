package com.losingtimeapps.javahub.ui.home.repository

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.losingtimeapps.javahub.common.presentation.BaseInjectingActivity
import com.losingtimeapps.javahub.ui.utils.EndlessScrollListener
import com.losingtimeapps.presentation.model.RepositoryModel
import com.losingtimeapps.presentation.ui.home.repository.RepositoryView
import com.losingtimeapps.presentation.ui.home.repository.RepositoryViewModel
import com.losingtimeapps.presentation.ui.home.repository.RepositoryViewModelFactory
import kotlinx.android.synthetic.main.fragment_repository.*
import javax.inject.Inject
import com.losingtimeapps.javahub.common.presentation.BaseGitHubFragment
import com.losingtimeapps.javahub.ui.home.HomeActivity

class RepositoryFragment : BaseGitHubFragment(), RepositoryView {

    lateinit var repositoriesListAdapter: RepositoriesListAdapter
    @Inject
    lateinit var repositoryViewModelFactory: RepositoryViewModelFactory

    companion object {
        fun newInstance(): RepositoryFragment {
            return RepositoryFragment()
        }
    }

    override fun updateRepoLiveData(repoListData: List<RepositoryModel>) {
        repositoriesListAdapter.updateData(repoListData)
    }

    override fun navigateToPullRequestView(ownerName: String, repoName: String) {
        (activity as HomeActivity).showPullRequestFragment(ownerName, repoName)
    }

    private fun configureRecyclerView(viewModel: RepositoryViewModel) {
        val llm = LinearLayoutManager(activity)
        rv_repositorys.layoutManager = llm
        rv_repositorys.adapter = repositoriesListAdapter
        rv_repositorys?.addOnScrollListener(initRecyclerViewScroll(viewModel, llm))

    }

    private fun initRecyclerViewScroll(
        viewModel: RepositoryViewModel,
        linearLayoutManager: LinearLayoutManager
    ): EndlessScrollListener {
        return object : EndlessScrollListener(linearLayoutManager) {
            override fun onLoadMore(current_page: Int) {
                if (!swipe_container.isRefreshing) {
                    Log.d("awdad", "----->Page:$current_page")
                    viewModel.getRepositorys(current_page, false)
                }
            }
        }
    }

    override fun onInject() {
        val activity = activity as BaseInjectingActivity<*>
        val componentCreator: RepositoryComponent.RepositoryComponentCreator? = activity.component
                as RepositoryComponent.RepositoryComponentCreator
        componentCreator!!.repositoryComponent().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, repositoryViewModelFactory)
            .get(RepositoryViewModel::class.java)
        repositoriesListAdapter = RepositoriesListAdapter(viewModel)
        configureRecyclerView(viewModel)
        configureSwipeRefresh(viewModel)
        viewModel.setView(this)
        viewModel.getRepositorys()
    }

    fun configureSwipeRefresh(viewModel: RepositoryViewModel) {
        swipe_container.setOnRefreshListener {
            repositoriesListAdapter.resetListAdapter()
            configureRecyclerView(viewModel)
            swipe_container.isRefreshing = false
            viewModel.getRepositorys(1, true)

        }
    }


}