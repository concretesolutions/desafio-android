package br.com.cavreti.desafio_android.useCase.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cavreti.desafio_android.R
import br.com.cavreti.desafio_android.data.Repository
import br.com.cavreti.desafio_android.applicationCore.CustomApplication
import br.com.cavreti.desafio_android.applicationCore.base.BaseActivity
import br.com.cavreti.desafio_android.useCase.repositoryPullRequests.PullRequestsActivity
import br.com.cavreti.desafio_android.util.OnAdapterItemClickListener
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View, OnAdapterItemClickListener {

    @Inject
    lateinit var presenter: HomePresenter

    private lateinit var adapter: RepositoryListAdapter

    var repositories: MutableList<Repository> = arrayListOf()
    private val firstListPage: Int = 1
    var currentListPage: Int = firstListPage

    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DaggerHomeComponent.builder()
            .serviceComponent((applicationContext as CustomApplication).getServiceComponent())
            .homeModule(HomeModule(this))
            .build()
            .inject(this)

       loadAdapter(arrayListOf())
       presenter.getRepositories(currentListPage)
       Log.d(TAG, "OnCreateCalled")
    }

    private fun loadAdapter(repositories: List<Repository>) {
        this.repositories.addAll(repositories)
        adapter = RepositoryListAdapter(this.repositories, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItem = manager.findLastCompletelyVisibleItemPosition()

                if(lastVisibleItem == repositories.size - 1) {
                    currentListPage++
                    Log.d(TAG, "CurrentList page: $currentListPage")
                    progressBar.visibility = View.VISIBLE
                    presenter.getRepositories(currentListPage)
                }
            }
        })
    }

    override fun loadRepositories(repositories: List<Repository>) {
        progressBar.visibility = View.GONE
        this.repositories.addAll(repositories)
        if(currentListPage == firstListPage){
            loadAdapter(this.repositories)
        }else{
            adapter.notifyItemInserted(this.repositories.size - 1)
        }
    }

    override fun loadRepositoriesFailed() {
        progressBar.visibility = View.GONE
        tryAgainLayout.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun onItemClick(item: Any, position: Int) {
        val repository = item as Repository
        val intent = Intent(this, PullRequestsActivity::class.java )
        intent.putExtra("repository", repository)
        startActivity(intent)
        overridePendingTransition(R.anim.enter, R.anim.exit)
    }

    fun tryAgain(view : View) {
        presenter.getRepositories(currentListPage)
        progressBar.visibility = View.VISIBLE
        tryAgainLayout.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}