package com.dobler.desafio_android.ui.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dobler.desafio_android.R
import com.dobler.desafio_android.data.model.GithubRepository
import com.dobler.desafio_android.ui.repository.adapter.RepositoryListAdapter
import com.dobler.desafio_android.util.paging.NetworkState
import kotlinx.android.synthetic.main.fragment_repository_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListRepositoryFragment : Fragment() {

    private val viewModel: ListRepositoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycleView()
        initSwipeToRefresh()
        viewModel.loadList()
    }


    private fun initSwipeToRefresh() {
        viewModel.refreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == NetworkState.LOADING
        })
        swipe_refresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    fun setUpRecycleView() {

        val adapter = RepositoryListAdapter {
            //            val action = RepositoryListFragmentDirections.actionMovieListFragmentToDetailsFragment(it.id)
//            findNavController().navigate(action)
//            Log.e("TAG", "Navigate")
        };

        rvMainRepositoryList.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(context)
        }

        viewModel.githubRepositories.observe(this,
            Observer<PagedList<GithubRepository>> {
                adapter.submitList(it)
            })

        viewModel.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
        })
    }


}
