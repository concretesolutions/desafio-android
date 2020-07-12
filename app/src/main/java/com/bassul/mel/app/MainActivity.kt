package com.bassul.mel.app


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

     lateinit var repository : RepoRepository
    lateinit var  interactor : RepoInteractor

    private var adapter: RepositoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = RepoRepository(GithubAPI())
        interactor = RepoInteractor(this, repository)

        initRecyclerView()
        initRepositoriesCard()
    }

    fun initRecyclerView() {
        recyclerViewRepositories.layoutManager = LinearLayoutManager(this)
        adapter = RepositoryAdapter(this, mutableListOf())
    }

    fun initRepositoriesCard(){
        interactor.loadRepositories()
    }

    fun showCard(repositories: ArrayList<Item>) {
        adapter = RepositoryAdapter(this, repositories)
        recyclerViewRepositories.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }
}
