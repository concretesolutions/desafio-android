package com.example.githubtest.features.repository

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtest.R
import com.example.githubtest.data.model.Repository
import com.example.githubtest.data.model.ViewStateModel
import com.example.githubtest.features.PullRequestActivity
import kotlinx.android.synthetic.main.activity_repository.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryActivity : AppCompatActivity(),RepositoryClickListener {

    private lateinit var layoutManager: LinearLayoutManager
    private var repositoryAdapter: RepositoryAdapter = RepositoryAdapter(ArrayList(), this)
    private val repositoryViewModel: RepositoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        initRepositories()
        initObservable()
    }

    private fun initObservable() {

        repositoryViewModel.getRepository().observe(this, Observer { stateModel ->
            when (stateModel.status) {
                ViewStateModel.Status.LOADING -> {
                    repositoryAdapter.startLoading()
                }
                ViewStateModel.Status.SUCCESS -> {
                    repositoryAdapter.stopLoading()
                    stateModel.model?.items?.let { repositories ->
                        if (repositories.isEmpty()) showEmptyRepositoryMessage()
                        else {
                            repositoryAdapter.addRepositories(repositories)
                            recyclerRepository.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                    if (dy > 0) {
                                        val visibleItemCount = layoutManager.childCount
                                        val totalItemCount = layoutManager.itemCount
                                        val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && !repositoryAdapter.isLoading()) {
                                            repositoryViewModel.loadRepository()
                                        }
                                    }
                                }
                            })
                        }
                    }?: run{
                        showEmptyRepositoryMessage()
                    }
                }
                ViewStateModel.Status.ERROR -> {
                    repositoryAdapter.stopLoading()
                    showNetworkError()
                }
            }
        })
    }

    private fun showNetworkError() {
        Toast.makeText(this,"Verifique sua conexão com a internet",Toast.LENGTH_LONG).show()

    }

    private fun showEmptyRepositoryMessage() {
        Toast.makeText(this,"Não foram encontrados registros",Toast.LENGTH_LONG).show()

    }

    private fun initRepositories() {
        layoutManager = LinearLayoutManager(this)
        recyclerRepository.layoutManager = layoutManager
        recyclerRepository.adapter = repositoryAdapter
        recyclerRepository.setHasFixedSize(true)
        }

    override fun onClick(repository: Repository) {
        try {
            val i = Intent(this, PullRequestActivity::class.java)
//            i.putExtra(INTENT_REPOSITORY_NAME, repository.name)
//            i.putExtra(INTENT_REPOSITORY_OWNER_NAME, repository.owner.login)
            startActivity(i)
        } catch (e: ArrayIndexOutOfBoundsException) {
            e.printStackTrace()
        }    }
}


