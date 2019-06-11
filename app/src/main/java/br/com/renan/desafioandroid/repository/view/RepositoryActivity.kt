package br.com.renan.desafioandroid.repository.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import br.com.renan.desafioandroid.R
import br.com.renan.desafioandroid.model.data.Repository
import br.com.renan.desafioandroid.model.data.RepositoryItemsList
import br.com.renan.desafioandroid.repository.presentation.IRepositoryContract
import br.com.renan.desafioandroid.repository.presentation.RepositoryPresenter
import br.com.renan.desafioandroid.util.PaginationScroll
import kotlinx.android.synthetic.main.activity_repository.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.toolbar.*

class RepositoryActivity : AppCompatActivity(), IRepositoryContract.View {

    private val repositoryPresenter = RepositoryPresenter()
    private lateinit var repositoryAdapter: RepositoryAdapter
    private val listRepository = ArrayList<Repository>()
    private var releasedLoad: Boolean = true
    private var page = 1
    private lateinit var toolbar: Toolbar

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        toolbar = findViewById(R.id.include_toolbar)

        setupToolbar(toolbar)

        repositoryPresenter.bind(this)

        init(page)

        setupLayout()

        initListeners()
    }

    private fun setupToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar_title.text = getString(R.string.title_toolbar_repository)
    }

    private fun setupLayout() {
        repositoryAdapter = RepositoryAdapter(listRepository)
        repositoryRecycler.itemAnimator = DefaultItemAnimator()
        linearLayoutManager = LinearLayoutManager(this)
        repositoryRecycler.layoutManager = linearLayoutManager
        repositoryRecycler.adapter = repositoryAdapter
    }

    private fun initListeners() {
        ivError.setOnClickListener {
            repositoryPresenter.requestRepositoryData(page)
        }

        repositoryRecycler.addOnScrollListener(object : PaginationScroll(linearLayoutManager) {
            override fun loadMoreItems() {
                releasedLoad = false
                pbBottom.visibility = View.VISIBLE
                page++
                init(page)
            }

            override fun hideMoreItems() {
                pbRepository.visibility = View.GONE
            }

            override fun isLoading(): Boolean {
                return releasedLoad
            }
        })
    }

    private fun init(page: Int) {
        repositoryPresenter.requestRepositoryData(page)
    }

    override fun repositorySuccess(repositoryList: RepositoryItemsList) {
        listRepository.addAll(repositoryList.repositoryItemsList)
        releasedLoad = true
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
        pbBottom.visibility = View.GONE
    }

    override fun showRepositorySucess() {
        pbRepository.visibility = View.GONE
        repositoryRecycler.visibility = View.VISIBLE
        pbBottom.visibility = View.GONE
    }
}
