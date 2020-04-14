package br.com.bernardino.githubsearch.ui.activities

import android.content.Intent
import android.net.Uri
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
import br.com.bernardino.githubsearch.di.dataModule
import br.com.bernardino.githubsearch.model.EXTRA_REPOSITORY
import br.com.bernardino.githubsearch.repository.ReposRepositoryImpl
import br.com.bernardino.githubsearch.viewmodel.PullRequestActivityViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

class PullRequestActivity : BaseActivity() {

    lateinit var mBinding: ActivityPullrequestBinding
    lateinit var mAdapter: PullRequestListAdapter

    private val koinFeatures by lazy {
        loadKoinModules(dataModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pullrequest)
        injectFeatures()

        val repository = intent.getParcelableExtra<RepositoryDatabase>(EXTRA_REPOSITORY)
        val reposImpl : ReposRepositoryImpl by inject()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = repository.name

        val mPullRequestActivityViewModel = ViewModelProviders.of(this, viewModelFactory {
            PullRequestActivityViewModel(
                reposImpl,
                creator = repository.ownerUserFirstName,
                repository = repository.name
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
        mAdapter = PullRequestListAdapter(this) { url : String -> clickListener(url)}
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

    private fun clickListener(url: String) {
        val webIntent: Intent = Uri.parse(url).let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }

        startActivity(webIntent)
    }

    private fun injectFeatures() = koinFeatures
}
