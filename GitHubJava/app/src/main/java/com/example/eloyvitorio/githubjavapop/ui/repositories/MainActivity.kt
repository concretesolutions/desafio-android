package com.example.eloyvitorio.githubjavapop.ui.repositories

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.AbsListView
import com.example.eloyvitorio.githubjavapop.R
import com.example.eloyvitorio.githubjavapop.ui.pullrequest.PullRequestsListActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_message_and_load_retry.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val POSITION_OF_LAYOUT_PROGRESS_BAR = 0
private const val POSITION_OF_LAYOUT_REPO_LIST = 1
private const val POSITION_OF_LAYOUT_ERROR = 2

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepositoriesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val viewModel: RepositoriesViewModel by viewModel()

    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var pastVisiblesItems = 0
    private var isScrolling = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolbar()
        setUpRecyclerView()
        setUpObservables()
        setUpScrollListener()
    }

    private fun setUpScrollListener() {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        isScrolling = true
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    visibleItemCount = linearLayoutManager.childCount
                    totalItemCount = linearLayoutManager.itemCount
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()

                    if (isScrolling && (visibleItemCount + pastVisiblesItems == totalItemCount)) {
                        isScrolling = false
                        loadMoreItems()
                    }
                }
            })
    }

    private fun loadMoreItems() {
        viewFlipperMainRepository.displayedChild = POSITION_OF_LAYOUT_PROGRESS_BAR
        viewModel.fetchRepositories()
    }

    private fun setUpObservables() {
        viewModel.repositoryList.observe(this, Observer { list ->
            list?.let {
                viewFlipperMainRepository.displayedChild = POSITION_OF_LAYOUT_REPO_LIST
                adapter.addAll(it)
            }
        })

        viewModel.error.observe(this, Observer { message ->
            textViewErrorLoadMessage.text = message
            viewFlipperMainRepository.displayedChild = POSITION_OF_LAYOUT_ERROR
            buttonErrorLoadRetry.setOnClickListener {
                reloadFromError()
            }
        })
        viewModel.fetchRepositories()
    }

    private fun openPullRequestListActivity(context: Context, userName: String, title: String) {
        val intent = PullRequestsListActivity.newIntent(context, userName, title)
        this.startActivity(intent)
    }

    private fun reloadFromError() {
        loadMoreItems()
    }

    private fun setUpToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbar)
    }

    private fun setUpRecyclerView() {
        adapter = RepositoriesAdapter { userName, title ->
            openPullRequestListActivity(this, userName, title)
        }
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView = recyclerViewMainRepository
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }
}
