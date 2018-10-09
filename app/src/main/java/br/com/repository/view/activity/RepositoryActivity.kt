package br.com.repository.view.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil.getBinding
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import br.com.repository.R
import br.com.repository.constants.Constants
import br.com.repository.model.Repository
import br.com.repository.view.adapter.RepositoryAdapter
import br.com.repository.viewmodel.RepositoryViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RepositoryActivity : BaseActivity() {

    private val repositoryViewModel: RepositoryViewModel by viewModel()
    private val repositoryAdapter = RepositoryAdapter(onItemClicked())

    lateinit var linearLayoutManager: LinearLayoutManager

    var pastVisibleItems : Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var loadMore: Boolean = true

    override fun configureView() {
        linearLayoutManager = LinearLayoutManager(this)
        getBinding().include.rvRepository.layoutManager = linearLayoutManager
        getBinding().include.rvRepository.setHasFixedSize(true)
        getBinding().include.rvRepository.adapter = repositoryAdapter
    }

    override fun init() {
        repositoryViewModel.requestRepository()
        repositoryViewModel.getRepo().observe(this, Observer { lRepository ->
            repositoryAdapter.setListRepository(lRepository!!)
            loadMore = true
        })

        repositoryViewModel.showProgress().observe(this, Observer { isShow ->
            if (isShow!!) {
                getBinding().progress.visibility = View.GONE
                getBinding().include.root.visibility = View.VISIBLE
            }
        })

        getBinding().include.rvRepository.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = linearLayoutManager.childCount
                totalItemCount = linearLayoutManager.itemCount
                pastVisibleItems  = linearLayoutManager.findFirstCompletelyVisibleItemPosition()

                if (dy > 0) {
                    if (loadMore && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        repositoryViewModel.callRepository()
                        loadMore = false
                    }
                }
            }
        })
    }

    private fun onItemClicked(): (Repository) -> Unit {
        return { repository ->
            startActivity(Intent(this,
                    PullRequestActivity::class.java)
                    .putExtra(Constants.OBJECT, repository))
        }
    }

    override fun getLayoutId() = R.layout.activity_base_activity
    override fun getTitleToolbar(): String = getString(R.string.app_name)

    override fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
