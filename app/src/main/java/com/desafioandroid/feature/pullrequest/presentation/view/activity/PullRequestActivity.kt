package com.desafioandroid.feature.pullrequest.presentation.view.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafioandroid.R
import com.desafioandroid.core.base.BaseActivity
import com.desafioandroid.core.helper.observeResource
import com.desafioandroid.core.util.rotationAnimation
import com.desafioandroid.data.model.pullrequest.entity.PullRequestResponse
import com.desafioandroid.feature.pullrequest.presentation.view.adapter.PullRequestAdapter
import com.desafioandroid.feature.pullrequest.presentation.viewmodel.PullRequestViewModel
import kotlinx.android.synthetic.main.activity_pull_request.*
import kotlinx.android.synthetic.main.activity_pull_request.swipe_refresh
import kotlinx.android.synthetic.main.layout_reload.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestActivity : BaseActivity() {

    private val viewModel by viewModel<PullRequestViewModel>()

    private val pullRequestAdapter by lazy {
        PullRequestAdapter(pullRequestList)
    }

    private val pullRequestList = arrayListOf<PullRequestResponse>()

    private var nameUser: String = ""
    private var nameRepository: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        setToolbar(title = R.string.title_toolbar_pull_request)

        catchIntent()

        initViewModel()
        iniUi()
    }

    private fun catchIntent() {
        intent?.let {
            nameUser = it.getStringExtra("name_user")
            nameRepository = it.getStringExtra("name_repository")
        }
    }

    private fun initViewModel() {
        viewModel.fetchPullRequest(nameUser, nameRepository)
        viewModel.getList.observeResource(this,
            onSuccess = {
                populateList(it)
                showSuccess()
            },
            onLoading = {
                showLoading(it)
            },
            onError = {
                showError()
            }
        )
    }

    private fun iniUi() {
        with(recycler_pull_request) {
            this.adapter = pullRequestAdapter
            val linearLayoutManager = LinearLayoutManager(this@PullRequestActivity)
            this.layoutManager = linearLayoutManager
        }

        swipeRefresh()
    }

    private fun populateList(pullRequestList: List<PullRequestResponse>) {
        this.pullRequestList.addAll(pullRequestList)
        pullRequestAdapter.notifyDataSetChanged()
    }

    private fun clearListAndAdd() {
        pullRequestAdapter.clear(pullRequestList)
        viewModel.fetchPullRequest(nameUser, nameRepository)
    }

    private fun swipeRefresh() {
        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({
                clearListAndAdd()

                swipe_refresh.isRefreshing = false
            }, 1000)
        }
    }

    private fun showSuccess() {
        recycler_pull_request.visibility = View.VISIBLE
        include_layout_reload.visibility = View.GONE

        showListEmpty()
    }

    private fun showLoading(isVisibility: Boolean) {
        include_layout_loading.visibility = if (isVisibility) View.VISIBLE else View.GONE
    }

    private fun showError() {
        include_layout_reload.visibility = View.VISIBLE
        recycler_pull_request.visibility = View.GONE

        showLoadingAndHideButtonRefresh(false)

        image_refresh_full_screen.setOnClickListener { view ->
            view.rotationAnimation()

            clearListAndAdd()

            showLoadingAndHideButtonRefresh(true)
            include_layout_loading.visibility = View.GONE
        }
    }

    private fun showListEmpty() {
        val listIsEmpty = pullRequestList.isEmpty()

        include_layout_empty.visibility = if (listIsEmpty) View.VISIBLE else View.GONE
        swipe_refresh.visibility = if (listIsEmpty) View.GONE else View.VISIBLE
    }

    private fun showLoadingAndHideButtonRefresh(isVisibility: Boolean) {
        progress_loading_full_screen.visibility = if (isVisibility) View.VISIBLE else View.GONE
        image_refresh_full_screen.visibility = if (isVisibility) View.GONE else View.VISIBLE
    }
}
