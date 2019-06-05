package br.com.renan.desafioandroid.repository.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import br.com.renan.desafioandroid.R
import br.com.renan.desafioandroid.model.data.Repository
import br.com.renan.desafioandroid.model.data.RepositoryItensList
import br.com.renan.desafioandroid.repository.presentation.IRepositoryContract
import br.com.renan.desafioandroid.repository.presentation.RepositoryPresenter
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : AppCompatActivity(), IRepositoryContract.View {

    private val repositoryPresenter = RepositoryPresenter()
    private lateinit var repositoryAdapter: RepositoryAdapter
    private val listRepository = ArrayList<Repository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

//        initView()

        repositoryPresenter.bind(this)

        repositoryPresenter.requestRepositoryData(1)

    }

    override fun populateRepositorySuccess(repositoryList: RepositoryItensList) {
        listRepository.addAll(repositoryList.repositoryItensList)
        repositoryRecycler.itemAnimator = DefaultItemAnimator()
        repositoryRecycler.layoutManager = LinearLayoutManager(this)
        repositoryAdapter = RepositoryAdapter(listRepository)
        repositoryRecycler.adapter = repositoryAdapter
        repositoryAdapter.notifyDataSetChanged()
    }

    private fun initView() {

    }

}
