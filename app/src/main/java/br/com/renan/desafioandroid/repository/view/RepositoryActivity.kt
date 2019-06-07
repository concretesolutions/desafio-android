package br.com.renan.desafioandroid.repository.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.renan.desafioandroid.R
import br.com.renan.desafioandroid.model.data.Repository
import br.com.renan.desafioandroid.model.data.RepositoryItemsList
import br.com.renan.desafioandroid.repository.presentation.IRepositoryContract
import br.com.renan.desafioandroid.repository.presentation.RepositoryPresenter
import kotlinx.android.synthetic.main.activity_repository.*
import kotlinx.android.synthetic.main.error_layout_repository.*

class RepositoryActivity : AppCompatActivity(), IRepositoryContract.View {

    private val repositoryPresenter = RepositoryPresenter()
    private lateinit var repositoryAdapter: RepositoryAdapter
    private val listRepository = ArrayList<Repository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        init()

        ivError.setOnClickListener {
            repositoryPresenter.requestRepositoryData(1)
        }
    }

    private fun init() {
        repositoryPresenter.bind(this)
        repositoryPresenter.requestRepositoryData(1)
    }

    override fun repositorySuccess(repositoryList: RepositoryItemsList) {
        listRepository.addAll(repositoryList.repositoryItemsList)
        repositoryRecycler.itemAnimator = DefaultItemAnimator()
        repositoryRecycler.layoutManager = LinearLayoutManager(this)
        repositoryAdapter = RepositoryAdapter(listRepository)
        repositoryRecycler.adapter = repositoryAdapter
        repositoryAdapter.notifyDataSetChanged()
    }

    override fun showRepositoryLoading() {
        pbRepository.visibility = View.VISIBLE
        include_error_repository.visibility = View.GONE
    }

    override fun showRepositoryError() {
        include_error_repository.visibility = View.VISIBLE
        pbRepository.visibility = View.GONE
        repositoryRecycler.visibility = View.GONE
    }

    override fun showRepositorySucess() {
        pbRepository.visibility = View.GONE
        repositoryRecycler.visibility = View.VISIBLE
    }
}
