package br.com.arthur.githubapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.arthur.githubapp.R
import br.com.arthur.githubapp.databinding.ActivityMainBinding
import br.com.arthur.githubapp.model.GitRepository
import br.com.arthur.githubapp.ui.adapters.GitRepositoryAdapter
import br.com.arthur.githubapp.ui.viewmodel.RepositoryViewModel

class MainActivity : BaseActivity() {

    private lateinit var bind: ActivityMainBinding

    private lateinit var repositoryViewModel: RepositoryViewModel

    private var adapter = GitRepositoryAdapter()

    private var repositoryList: List<GitRepository> = emptyList()
    private var page: Int = 1
    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        configureData()
        showLoadingDialog()
        repositoryViewModel.requestRepositories(page)
    }

    private fun configureData() {
        repositoryViewModel = RepositoryViewModel(this)
        configureComponents()
        configureObservers()
    }

    private fun configureComponents() {
        val layoutManager = LinearLayoutManager(this)
        bind.repositoryList.layoutManager = layoutManager

        bind.repositoryList.itemAnimator = DefaultItemAnimator()
        bind.repositoryList.adapter = adapter

        bind.repositoryList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastItem = layoutManager.findLastVisibleItemPosition()
                if (lastItem == adapter.itemCount - 1 && !isLoading) {
                    showLoadingDialog()
                    repositoryViewModel.requestRepositories(repositoryViewModel.currentPage + 1)
                }
            }
        })

        bind.errorMessage.setOnClickListener {
            repositoryViewModel.reset()
            showLoadingDialog()
        }
    }

    private fun configureObservers() {
        showLoadingDialog()
        repositoryViewModel.mutableRepositoryList.observe(this, Observer { list ->
            dismissLoadingDialog()
            repositoryList = list
            bind.repositoryList.visibility = View.VISIBLE
            bind.errorMessage.visibility = View.INVISIBLE
            list.forEach {
                Log.v("TAG - Name :", it.name)
                Log.v("TAG - Description :", it.description)
                Log.v("TAG - Forks :", it.forks.toString())
                Log.v("TAG - Stars :", it.stargazersCount.toString())
                if (list.isNotEmpty()) {
                    adapter.setGitRepositoryList(list)
                }
            }
        })

        repositoryViewModel.mutableErrorMessage.observe(this, Observer { error ->
            dismissLoadingDialog()
            Log.e("Erro: ", " -> $error")
            if (repositoryList.isEmpty()) {
                bind.errorMessage.text = error
                bind.errorMessage.visibility = View.VISIBLE
                bind.repositoryList.visibility = View.INVISIBLE
            } else {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        })

    }
}
