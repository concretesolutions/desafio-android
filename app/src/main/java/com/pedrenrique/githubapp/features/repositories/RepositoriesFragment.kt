package com.pedrenrique.githubapp.features.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedrenrique.githubapp.R
import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.ext.supportActionBar
import com.pedrenrique.githubapp.core.ext.supportActivity
import com.pedrenrique.githubapp.core.platform.Adapter
import com.pedrenrique.githubapp.core.platform.EndlessRecyclerViewScrollListener
import com.pedrenrique.githubapp.features.common.BaseListFragment
import com.pedrenrique.githubapp.features.common.TypesFactoryAdapter
import kotlinx.android.synthetic.main.fragment_repositories.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesFragment : BaseListFragment() {
    private val repoViewModel by currentScope.viewModel<RepositoriesViewModel>(this)

    private val repoAdapter by lazy {
        Adapter(TypesFactory())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_repositories, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        repoViewModel.load()
        repoViewModel.state.observe(this, Observer {
            it?.data?.showErrorIfNeeded()
            repoAdapter.replace(it?.data ?: listOf())
        })
        rvRepositories.setup(repoAdapter) {
            repoViewModel.loadMore()
        }
    }

    inner class TypesFactory : TypesFactoryAdapter() {
        override fun click(repo: Repository) {
            val act = activity ?: return
            val navController = Navigation.findNavController(act, R.id.navHostFragment)
            val showPullRequests = RepositoriesFragmentDirections.showPullRequests(repo)
            navController.navigate(showPullRequests)
        }

        override fun click(failure: Failure) {
            when (failure) {
                Failure.Empty ->
                    supportActivity?.onBackPressed()
                is Failure.Full ->
                    repoViewModel.load()
                is Failure.Item ->
                    repoViewModel.loadMore()
            }
        }
    }
}