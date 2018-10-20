package br.com.caiodev.desafioAndroid.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.caiodev.desafioAndroid.R
import br.com.caiodev.desafioAndroid.interfaces.RecyclerViewOnClick
import br.com.caiodev.desafioAndroid.model.RepositoryItemModel
import br.com.caiodev.desafioAndroid.model.RepositoryListAdapter
import br.com.caiodev.desafioAndroid.model.viewModel.RepositoryListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import utils.MasterActivity

class MainActivity : MasterActivity(), RecyclerViewOnClick<RepositoryItemModel> {

    private lateinit var viewModel: RepositoryListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        setupViewModel()
        populateView()
    }

    override fun setupView() {
        githubJavaRepoRecyclerView.setHasFixedSize(true)
    }

    override fun setupViewModel() {

        viewModel = ViewModelProviders.of(this).get(RepositoryListViewModel::class.java)

        repositoryListProgressBar.visibility = View.VISIBLE

        viewModel.getRepos("language:Java",
            "stars", 1, 15
        )

        viewModel.state.observe(this, Observer { state ->

            when (state) {

                RepositoryListViewModel.onReposReceived -> {
                    githubJavaRepoRecyclerView.adapter = RepositoryListAdapter(viewModel, this)
                    githubJavaRepoRecyclerView.adapter?.notifyDataSetChanged()
                    repositoryListProgressBar.visibility = View.GONE
//                    viewModel.getOwnerData(viewModel.getOwnerLogin())
                }

                RepositoryListViewModel.onReposReceivedError -> {
                    repositoryListProgressBar.visibility = View.GONE
                }

                RepositoryListViewModel.onRepoOwnerDataReceived -> {
                    githubJavaRepoRecyclerView.adapter = RepositoryListAdapter(viewModel, this)
                    githubJavaRepoRecyclerView.adapter?.notifyDataSetChanged()
                    repositoryListProgressBar.visibility = View.GONE
                }

                RepositoryListViewModel.onRepoOwnerDataReceivedError -> {
                    repositoryListProgressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun populateView() {

    }

    override fun onItemClickListener(view: View, position: Int, source: RepositoryItemModel?) {

    }
}