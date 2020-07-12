package com.bassul.mel.app.repositoriesList.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bassul.mel.app.*
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.interactor.RepoInteractorImpl
import com.bassul.mel.app.repositoriesList.RepoPresenterImpl
import com.bassul.mel.app.repositoriesList.RepositoriesListContract
import com.bassul.mel.app.repositoriesList.repository.RepoRepositoryImpl
import com.bassul.mel.app.repositoriesList.view.adapter.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), RepositoriesListContract.View {

     lateinit var repository : RepoRepositoryImpl
    lateinit var  interactor : RepoInteractorImpl
    lateinit var  presenter : RepoPresenterImpl

    private var adapter: RepositoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = RepoPresenterImpl(this)
        repository = RepoRepositoryImpl(GithubAPI())
        interactor = RepoInteractorImpl(presenter, repository)

        initRecyclerView()
        initRepositoriesCard()
    }

   override fun initRecyclerView() {
        recyclerViewRepositories.layoutManager = LinearLayoutManager(this)
        adapter = RepositoryAdapter(this, mutableListOf())
    }

    override  fun initRepositoriesCard(){
        interactor.loadRepositories()
    }

    override  fun showCard(repositories: ArrayList<Item>) {
        adapter = RepositoryAdapter(this, repositories)
        recyclerViewRepositories.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }
}
