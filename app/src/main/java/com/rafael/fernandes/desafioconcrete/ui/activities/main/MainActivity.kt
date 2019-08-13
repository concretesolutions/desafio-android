package com.rafael.fernandes.desafioconcrete.ui.activities.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafael.fernandes.desafioconcrete.R
import com.rafael.fernandes.desafioconcrete.databinding.ActivityMainBinding
import com.rafael.fernandes.desafioconcrete.extentions.showToast
import com.rafael.fernandes.desafioconcrete.presentation.resources.Resource
import com.rafael.fernandes.desafioconcrete.ui.activities.pullrequest.PullRequestActivity
import com.rafael.fernandes.desafioconcrete.ui.activities.pullrequest.PullRequestActivity.Companion.KEY_NAME
import com.rafael.fernandes.desafioconcrete.ui.activities.pullrequest.PullRequestActivity.Companion.KEY_REPOSITORY_NAME
import com.rafael.fernandes.desafioconcrete.ui.adapters.ListRepositoryAdapter
import com.rafael.fernandes.desafioconcrete.ui.base.BaseActivity
import com.rafael.fernandes.desafioconcrete.ui.custom.compoments.RecyclerViewEndlessScroll
import com.rafael.fernandes.domain.model.GitRepositories
import com.rafael.fernandes.domain.model.Item
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<ActivityMainBinding, RepositoriesViewModel>() {

    private val itemClick: (Item) -> Unit =
        {
            val bundle = Bundle()
            bundle.putString(KEY_NAME, it.owner?.login)
            bundle.putString(KEY_REPOSITORY_NAME, it.name)
            gotoNextScreen(PullRequestActivity::class.java, bundle, false)

        }

    private val adapter = ListRepositoryAdapter(itemClick)


    override fun layoutId(): Int = R.layout.activity_main

    lateinit var recyclerView: RecyclerViewEndlessScroll

    override fun myOnCreate(savedInstanceState: Bundle?) {
        title = getString(R.string.repository_label)
        setupRecyclerView()
        observeRepositories()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_LIT)) {
            val list = savedInstanceState.getSerializable(KEY_LIT) as List<Item>
            adapter.updateList(list)
            return
        }

        getRepositories()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupRecyclerView() {
        recyclerView = recycler_view_endless_scroll
        val layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )

        recyclerView.addEndlessScroll(layoutManager) {
            getRepositories()
        }
        swipe_refresh_layout.setOnRefreshListener {
            getRepositories(true)
        }

        recyclerView.addItemDecoration(divider)
        recyclerView.adapter = adapter
        adapter.submitList(null)
    }

    private fun getRepositories(refresh: Boolean = false) {
        mViewModel.getRepositoriesGit(refresh)
    }

    private fun getFavoriteRepositories(refresh: Boolean = false) {
        mViewModel.getRepositories(refresh)
    }

    private fun observeRepositories() {
        mViewModel.mRepositoryGitObservable.observe(this, Observer {
            updateView(it)
            getFavoriteRepositories()
        })

        mViewModel.mRefreshObservable.observe(this, Observer { refreshList ->
            if (refreshList) {
                adapter.resetDataList()
            }
        })

        mViewModel.mRepositoryListObservable.observe(this, Observer {
            adapter.updateItems(it.data)
        })

        adapter.imageViewItemClick = {
            mViewModel.saveRepository(it)
        }
    }

    override fun onLoading() {
        swipe_refresh_layout.isRefreshing = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (adapter.getList().isNotEmpty()) {
            outState.putSerializable(KEY_LIT, adapter.getList())
        }
    }

    override fun <H> onSuccess(data: H?) {
        swipe_refresh_layout.isRefreshing = false
        val item = data as GitRepositories

        if (item.items.isNullOrEmpty()) {
            recyclerView.setNoMoreItems(true)
            return
        }

        adapter.updateList(item.items)
    }

    override fun onError(message: String?) {
        swipe_refresh_layout.isRefreshing = false
        message?.run {
            showToast(this)
        }
    }

    private fun updateView(resource: Resource<GitRepositories>?) {
        resource?.let {
            onStateChange(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item?.itemId) {
            R.id.favorite -> {
                runOnUiThread {
                    gotoNextScreen(GitRepositoriesActivity::class.java, null, false)
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    companion object {
        private val KEY_LIT = "list"
    }
}
