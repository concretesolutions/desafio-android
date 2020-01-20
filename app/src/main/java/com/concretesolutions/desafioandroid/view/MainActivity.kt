package com.concretesolutions.desafioandroid.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.concretesolutions.desafioandroid.R
import com.concretesolutions.desafioandroid.adapters.RepositoryAdapter
import com.concretesolutions.desafioandroid.model.Repository
import com.concretesolutions.desafioandroid.viewmodel.RepositoriesViewModel
import com.concretesolutions.desafioandroid.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var hasMore: Boolean = true
    private val RVPOSITION: String = "rvposition"
    private val REPOSITORIESPARCEL: String = "repositoriesparcel"
    private var page: Int = 1
    private val sortType: String = "stars"
    private val searchTerm: String = "language:Java"
    private lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var repositoriesViewModel: RepositoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapters()
        initView()
        loadData()

    }

    private fun loadData() {
        loadPageRepos()
    }

    private fun initAdapters() {

        repositoriesViewModel = RepositoriesViewModel(applicationContext)

        repositoryAdapter = RepositoryAdapter(object : RepositoryAdapter.OnItemClickListener {
            override fun onItemClick(repositoryViewModel: RepositoryViewModel) {
                repoClicked(repositoryViewModel)
            }
        })

        repositoriesViewModel.getRepos().observe(this, Observer {
            progressBar.visibility = View.GONE
            repositoryAdapter.updateRepositories(it!!.repositories)
        })
        repositoriesViewModel.getLoadStatus().observe(this, Observer {
            it?.let {
                progressBar.visibility = View.GONE
                hasMore = !it.finished
                if (!it.message.isNullOrEmpty()) {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

    }

    private fun loadPageRepos() {
        progressBar.visibility = View.VISIBLE
        repositoriesViewModel.loadRepos(searchTerm, sortType, page)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            it.putParcelableArrayList(
                REPOSITORIESPARCEL,
                ArrayList(repositoryAdapter.getRepositories())
            )
            it.putParcelable(RVPOSITION, rvRepositories.layoutManager!!.onSaveInstanceState())
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            val list = it.getParcelableArrayList<Repository>(REPOSITORIESPARCEL)
            if (list != null)
                repositoryAdapter.setRepositories(list.toList())
            rvRepositories.layoutManager!!.onRestoreInstanceState(it.getParcelable(RVPOSITION))
        }
        progressBar.visibility = View.GONE
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun initView() {

        val linearVertical = LinearLayoutManager(this)
        linearVertical.orientation = LinearLayoutManager.VERTICAL
        rvRepositories.layoutManager = linearVertical
        rvRepositories.adapter = repositoryAdapter

        rvRepositories.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (hasMore) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                    layoutManager.let {

                        if (it.findLastVisibleItemPosition() + 6 > it.itemCount) {
                            page++
                            loadPageRepos()
                        }
                    }
                }

            }

        })
    }

    private fun repoClicked(repositoryViewModel: RepositoryViewModel) {

        val intent = Intent(applicationContext, PullRequestActivity::class.java)
        intent.putExtra("repositoryName", repositoryViewModel.repositoryData.name)
        intent.putExtra("repositoryFullName", repositoryViewModel.repositoryData.fullName)
        intent.putExtra("owner", repositoryViewModel.repositoryData.owner.login)
        startActivity(intent)

    }
}
