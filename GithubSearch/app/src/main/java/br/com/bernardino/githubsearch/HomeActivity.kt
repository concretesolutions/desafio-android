package br.com.bernardino.githubsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.bernardino.githubsearch.adapter.ReposListAdapter
import br.com.bernardino.githubsearch.database.RepositoryDatabase
import br.com.bernardino.githubsearch.databinding.ActivityHomeBinding
import br.com.bernardino.githubsearch.model.EXTRA_REPOSITORY
import br.com.bernardino.githubsearch.viewmodel.HomeActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    lateinit var mAdapter: ReposListAdapter
    lateinit var mBinding: ActivityHomeBinding
    private val mHomeActivityViewModel by lazy {  ViewModelProviders.of(this).get(HomeActivityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        mBinding.viewmodel = mHomeActivityViewModel
        mBinding.lifecycleOwner = this

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)

        configureList()
        attachObserver()
    }

    private fun attachObserver() {
        mBinding.viewmodel?.isLoading?.observe(this, Observer<Boolean> {
            it?.let { showLoadingDialog(it) }
        })

        mBinding.viewmodel?.repoList?.observe(this, Observer {
            mAdapter.setReposListItems(it)
            mAdapter.notifyDataSetChanged()
        })

        mBinding.viewmodel?.eventNetworkError?.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    private fun configureList() {
        mBinding.rvRepos.addItemDecoration(DividerItemDecoration(mBinding.rvRepos.context, DividerItemDecoration.VERTICAL))
        mAdapter = ReposListAdapter(this) { repositoryDatabase : RepositoryDatabase -> clickListener(repositoryDatabase)  }
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.rvRepos.layoutManager = layoutManager
        mBinding.rvRepos.adapter = mAdapter
    }

    private fun showLoadingDialog(show: Boolean) {
        if (show) mBinding.pbHome.visibility = View.VISIBLE else mBinding.pbHome.visibility = View.GONE
    }

    private fun onNetworkError() {
        if(!mBinding.viewmodel?.isNetworkErrorShown?.value!!) {
            Snackbar.make(
                rv_repos,
                getString(R.string.network_error_msg),
                Snackbar.LENGTH_LONG
            ).show()
            mBinding.viewmodel?.onNetworkErrorShown()
        }
    }

    private fun clickListener(repository: RepositoryDatabase) {
        val intent = Intent (this, PullRequestActivity::class.java).apply {
            putExtra(EXTRA_REPOSITORY, repository)
        }
        startActivity(intent)

    }

}
