package com.haldny.githubapp.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.haldny.githubapp.R
import com.haldny.githubapp.common.EndlessScroll
import com.haldny.githubapp.common.observeResource
import com.haldny.githubapp.domain.model.Repository
import com.haldny.githubapp.presentation.pull.PullRequestActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private val mainAdapter by lazy {
        MainAdapter(
            onItemClickListener = { item ->
                val intent = Intent(this@MainActivity, PullRequestActivity::class.java)
                intent.putExtra("owner", item.owner.login)
                intent.putExtra("repository", item.name)
                startActivity(intent)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setToolbar(title = R.string.title_toolbar_main, showHomeAsUp = false)

        initViewModel()
        iniUi()
    }

    fun setToolbar(@StringRes title: Int, showHomeAsUp: Boolean) {
        my_toolbar.title = getString(title)
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initViewModel() {
        viewModel.getItems().observeResource(this,
            onSuccess = {
                populateList(it)
                showSuccess()
            },
            onLoading = {
                if (viewModel.isLoading) {
                    showLoading()
                }
            },
            onError = {
                showError(it)
            }
        )
    }

    private fun iniUi() {
        with(mainRecyclerView) {
            this.adapter = mainAdapter
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            this.addOnScrollListener(object : EndlessScroll(linearLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.nextPage()
                }

                override fun isLoading(): Boolean {
                    return viewModel.isLoading
                }

                override fun getTotalPageCount(): Int {
                    return 0
                }

                override fun isLastPage(): Boolean {
                    return viewModel.isLastPage
                }

                override fun hideItems() {
                    mainLoading.visibility = View.GONE
                }
            })

            this.layoutManager = linearLayoutManager
        }

        swipeRefresh()
    }

    private fun showSuccess() {
        mainRecyclerView.visibility = View.VISIBLE
        mainLoading.visibility = View.GONE
        viewModel.isLoading = true
    }

    private fun showLoading() {
        mainLoading.visibility = View.VISIBLE
    }

    private fun showError(t: Throwable) {
        when(t) {
            is HttpException -> {
                if (t.code() == 422){
                    paginationFinished()
                }
            }
        }
    }

    private fun paginationFinished() {
        viewModel.paginationFinished()
        mainAdapter.removeItemBottom()
    }

    private fun populateList(itemList: List<Repository>) {
        mainAdapter.addList(itemList)
    }

    private fun refresh() {
        mainAdapter.clearList()
        viewModel.refreshViewModel()
    }

    private fun swipeRefresh() {
        mainRefresh.setOnRefreshListener {
            Handler().postDelayed({
                refresh()
                mainRefresh.isRefreshing = false
            }, 1000)
        }
    }
}
