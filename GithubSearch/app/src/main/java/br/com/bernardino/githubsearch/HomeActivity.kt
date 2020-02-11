package br.com.bernardino.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.bernardino.githubsearch.adapter.ReposListAdapter
import br.com.bernardino.githubsearch.api.RetrofitInitializer
import br.com.bernardino.githubsearch.model.Repository
import br.com.bernardino.githubsearch.model.RepositoryBody
import br.com.bernardino.githubsearch.viewmodel.HomeActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    lateinit var mAdapter: ReposListAdapter
    lateinit var mHomeActivityViewModel: HomeActivityViewModel
    lateinit var mProgressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mHomeActivityViewModel = ViewModelProviders.of(this).get(HomeActivityViewModel::class.java)
        mProgressBar = pb_home

        attachObserver()
        configureList()
        //apiQueryRepos()
    }

    private fun attachObserver() {
        mHomeActivityViewModel.isLoading.observe(this, Observer<Boolean> {
            it?.let { showLoadingDialog(it) }
        })
        mHomeActivityViewModel.apiError.observe(this, Observer<Throwable> {
            it?.let { Snackbar.make(rv_repos, it.localizedMessage, Snackbar.LENGTH_LONG).show() }
        })

        mHomeActivityViewModel.getRepositories().observe(this, Observer {
            mAdapter.setReposListItems(it)
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun configureList() {
        var recyclerView = rv_repos
        mAdapter = ReposListAdapter(this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
    }

    private fun showLoadingDialog(show: Boolean) {
        if (show) mProgressBar.visibility = View.VISIBLE else mProgressBar.visibility = View.GONE
    }
}
