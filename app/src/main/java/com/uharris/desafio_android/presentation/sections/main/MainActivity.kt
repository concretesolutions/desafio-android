package com.uharris.desafio_android.presentation.sections.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.uharris.desafio_android.R
import com.uharris.desafio_android.domain.models.Repository
import com.uharris.desafio_android.presentation.base.BaseActivity
import com.uharris.desafio_android.presentation.base.ViewModelFactory
import com.uharris.desafio_android.presentation.sections.pull.PullRequestActivity
import com.uharris.desafio_android.presentation.state.Resource
import com.uharris.desafio_android.presentation.state.ResourceState
import com.uharris.desafio_android.utils.EndlessScrollListener
import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mainViewModel: MainViewModel

    private var page: Int = 1

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        setSupportActionBar(toolbar)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        mainViewModel.repositoriesLiveData.observe(this, Observer {
            handleDataState(it)
        })

        if (savedInstanceState != null) {
            page = savedInstanceState.getInt(STATE_PAGE, 0)
            val list = savedInstanceState.getParcelableArrayList<Repository>(STATE_LIST)
            setupUI(list ?: ArrayList())
        } else {
            setupUI(ArrayList())

            mainViewModel.fetchRepositories(page)
        }
    }

    private fun setupUI(items: ArrayList<Repository>) {
        val linearLayout = LinearLayoutManager(this)
        repositoryRecyclerView.layoutManager = linearLayout
        adapter = MainAdapter(items) { repository ->
            PullRequestActivity.startActivity(this, repository)
        }
        repositoryRecyclerView.adapter = adapter
        repositoryRecyclerView.clearOnScrollListeners()
        repositoryRecyclerView.addOnScrollListener(EndlessScrollListener({
            mainViewModel.fetchRepositories(
                ++page
            )
        }, linearLayout))
    }

    private fun handleDataState(resource: Resource<List<Repository>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                resource.data?.let {
                    adapter.setItems(it)
                }
            }
            ResourceState.LOADING -> {
            }
            ResourceState.ERROR -> {
                showMessage(resource.message ?: "")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putInt(STATE_PAGE, page)
        outState?.putParcelableArrayList(STATE_LIST, adapter.getItems())
    }

    companion object {
        const val STATE_PAGE = "page"
        const val STATE_LIST = "list"
    }
}
