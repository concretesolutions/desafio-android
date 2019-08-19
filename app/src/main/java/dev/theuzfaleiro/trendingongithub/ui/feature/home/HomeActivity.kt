package dev.theuzfaleiro.trendingongithub.ui.feature.home

import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import dev.theuzfaleiro.trendingongithub.R
import dev.theuzfaleiro.trendingongithub.ui.feature.home.adapter.RepositoryAdapter
import dev.theuzfaleiro.trendingongithub.ui.feature.home.viewmodel.HomeViewModel
import dev.theuzfaleiro.trendingongithub.ui.feature.home.viewmodel.HomeViewModelFactory
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PULL_REQUEST_OWNER_NAME
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PULL_REQUEST_REPOSITORY_NAME
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PullRequestActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter {
            startActivity(
                Intent(this, PullRequestActivity::class.java).putExtras(
                    bundleOf(
                        PULL_REQUEST_REPOSITORY_NAME to it.name,
                        PULL_REQUEST_OWNER_NAME to it.owner.userName
                    )
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUpRecyclerView()

        with(homeViewModel) {

            getRepositories().observe(this@HomeActivity, Observer {
                repositoryAdapter.submitList(it)
            })

            getLoading().observe(this@HomeActivity, Observer {
                viewFlipperRepository.displayedChild = it
            })

            fetchRepositories()
        }
    }

    private fun setUpRecyclerView() {
        with(recyclerViewRepository) {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}