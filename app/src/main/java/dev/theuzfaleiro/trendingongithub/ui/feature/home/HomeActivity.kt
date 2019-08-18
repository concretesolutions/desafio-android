package dev.theuzfaleiro.trendingongithub.ui.feature.home

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import dev.theuzfaleiro.trendingongithub.R
import dev.theuzfaleiro.trendingongithub.ui.feature.home.adapter.RepositoryAdapter
import dev.theuzfaleiro.trendingongithub.ui.feature.home.viewmodel.HomeViewModel
import dev.theuzfaleiro.trendingongithub.ui.feature.home.viewmodel.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }

    private val upcomingMoviesAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUpRecyclerView()

        homeViewModel.getRepositories().observe(this, Observer {
            upcomingMoviesAdapter.submitList(it)
        })
    }

    private fun setUpRecyclerView() {
        with(recyclerViewRepository) {
            adapter = upcomingMoviesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}