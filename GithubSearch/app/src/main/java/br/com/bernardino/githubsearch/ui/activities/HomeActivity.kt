package br.com.bernardino.githubsearch.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.bernardino.githubsearch.R
import br.com.bernardino.githubsearch.adapter.ReposListAdapter
import br.com.bernardino.githubsearch.database.RepositoryDatabase
import br.com.bernardino.githubsearch.databinding.ActivityHomeBinding
import br.com.bernardino.githubsearch.di.dataModule
import br.com.bernardino.githubsearch.di.databaseModule
import br.com.bernardino.githubsearch.di.networkModule
import br.com.bernardino.githubsearch.model.EXTRA_REPOSITORY
import br.com.bernardino.githubsearch.repository.ReposRepositoryImpl
import br.com.bernardino.githubsearch.viewmodel.HomeActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class HomeActivity : BaseActivity() {

    lateinit var mAdapter: ReposListAdapter
    lateinit var mBinding: ActivityHomeBinding
    val reposImpl: ReposRepositoryImpl by inject()
    lateinit var mHomeActivityViewModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mHomeActivityViewModel = ViewModelProviders.of(this, viewModelFactory {
                HomeActivityViewModel(reposImpl)
            }
        ).get(HomeActivityViewModel::class.java)

        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_home
        )
        mBinding.viewmodel = mHomeActivityViewModel
        mBinding.lifecycleOwner = this

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)

        configureList()
        attachObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(listOf(dataModule, networkModule, databaseModule))
    }

    private fun attachObserver() {
        mHomeActivityViewModel.isLoading.observe(this, Observer<Boolean> {
            it?.let { showLoadingDialog(it) }
        })

        mHomeActivityViewModel.repositories.observe(this, Observer {
            mAdapter.setReposListItems(it)
        })

        mHomeActivityViewModel.eventNetworkError.observe(
            this,
            Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })
    }

    private fun configureList() {
        mBinding.rvRepos.addItemDecoration(
            DividerItemDecoration(
                mBinding.rvRepos.context,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = ReposListAdapter(this) { repositoryDatabase: RepositoryDatabase ->
            clickListener(repositoryDatabase)
        }
        mBinding.rvRepos.adapter = mAdapter
    }

    private fun showLoadingDialog(show: Boolean) {
        if (show) mBinding.pbHome.visibility = View.VISIBLE else mBinding.pbHome.visibility =
            View.GONE
    }

    private fun onNetworkError() {
        if (!mHomeActivityViewModel.isNetworkErrorShown.value!!) {
            Snackbar.make(
                rv_repos,
                getString(R.string.network_error_msg),
                Snackbar.LENGTH_LONG
            ).show()
            mBinding.viewmodel?.onNetworkErrorShown()
        }
    }

    private fun clickListener(repository: RepositoryDatabase) {
        val intent = Intent(this, PullRequestActivity::class.java).apply {
            putExtra(EXTRA_REPOSITORY, repository)
        }
        startActivity(intent)
    }
}

