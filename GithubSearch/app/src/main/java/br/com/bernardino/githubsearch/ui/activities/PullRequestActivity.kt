package br.com.bernardino.githubsearch.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.bernardino.githubsearch.R
import br.com.bernardino.githubsearch.adapter.PullRequestListAdapter
import br.com.bernardino.githubsearch.database.RepositoryDatabase
import br.com.bernardino.githubsearch.databinding.ActivityPullrequestBinding
import br.com.bernardino.githubsearch.model.EXTRA_REPOSITORY
import br.com.bernardino.githubsearch.viewmodel.PullRequestActivityViewModel
import com.google.android.material.snackbar.Snackbar

class PullRequestActivity : BaseActivity() {

    lateinit var mBinding: ActivityPullrequestBinding
    lateinit var mAdapter: PullRequestListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pullrequest)

        val repository = intent.getParcelableExtra<RepositoryDatabase>(EXTRA_REPOSITORY)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = repository.name

        val mPullRequestActivityViewModel = ViewModelProviders.of(this, viewModelFactory {
            PullRequestActivityViewModel(
                application,
                creator = repository.ownerUserFirstName, repository = repository.name
            )
        }).get(PullRequestActivityViewModel::class.java)

        mBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_pullrequest
        )
        mBinding.viewmodel = mPullRequestActivityViewModel
        mBinding.lifecycleOwner = this

        configureList()
        attachObserver()
    }

    private fun configureList() {
        mBinding.rvPullrequest.addItemDecoration(
            DividerItemDecoration(
                mBinding.rvPullrequest.context,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = PullRequestListAdapter(this)
        mBinding.rvPullrequest.adapter = mAdapter
    }

    private fun attachObserver() {
        mBinding.viewmodel?.isLoading?.observe(this, Observer<Boolean> {
            it?.let { showLoadingDialog(it) }
        })

        mBinding.viewmodel?.pullRequestLiveData?.observe(this, Observer {
            mAdapter.setPullRequestListItems(it)
        })

        mBinding.viewmodel?.eventNetworkError?.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    private fun showLoadingDialog(show: Boolean) {
        if (show) mBinding.pbPullrequest.visibility =
            View.VISIBLE else mBinding.pbPullrequest.visibility = View.GONE
    }

    private fun onNetworkError() {
        if (!mBinding.viewmodel?.isNetworkErrorShown?.value!!) {
            Snackbar.make(
                mBinding.rvPullrequest,
                getString(R.string.network_error_msg),
                Snackbar.LENGTH_LONG
            ).show()
            mBinding.viewmodel?.onNetworkErrorShown()
        }
    }

}
