package com.pedrenrique.githubapp.features.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pedrenrique.githubapp.R
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.platform.Adapter
import com.pedrenrique.githubapp.core.platform.EndlessRecyclerViewScrollListener
import com.pedrenrique.githubapp.features.TypesFactoryImpl
import kotlinx.android.synthetic.main.fragment_repositories.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesFragment : Fragment() {
    private val repoViewModel by viewModel<RepositoriesViewModel>()

    private val repoAdapter by lazy {
        val listeners = Listeners()
        val typesFactory = TypesFactoryImpl(listeners)
        Adapter(typesFactory)
    }

    private val layoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(context)
    }

    private val endlessRecyclerViewScrollListener by lazy {
        EndlessScrollListener(layoutManager)
    }

    private val dividerItemDecoration by lazy {
        DividerItemDecoration(context, layoutManager.orientation)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_repositories, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvRepositories.layoutManager = layoutManager
        rvRepositories.addItemDecoration(dividerItemDecoration)
        rvRepositories.adapter = repoAdapter
        rvRepositories.setHasFixedSize(true)

        repoViewModel.state.observe(this, Observer {
            repoAdapter.replace(it?.data ?: listOf())
        })
        repoViewModel.load()

        rvRepositories.addOnScrollListener(endlessRecyclerViewScrollListener)
    }

    inner class Listeners : TypesFactoryImpl.OnClickListener {
        override fun onClick(repo: Repository) {
            // TODO
        }
    }

    inner class EndlessScrollListener(layoutManager: RecyclerView.LayoutManager) :
        EndlessRecyclerViewScrollListener(layoutManager) {
        override fun onLoadMore(totalItemsCount: Int, view: RecyclerView) {
            repoViewModel.loadMore()
        }
    }
}