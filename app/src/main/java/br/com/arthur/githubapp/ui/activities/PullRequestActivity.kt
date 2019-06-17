package br.com.arthur.githubapp.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.arthur.githubapp.R
import br.com.arthur.githubapp.databinding.ActivityPullRequestBinding
import br.com.arthur.githubapp.model.PullRequest
import br.com.arthur.githubapp.ui.adapters.PullRequestAdapter
import br.com.arthur.githubapp.ui.viewmodel.RepositoryViewModel
import br.com.arthur.githubapp.util.constants.Constants.KEY_OWNER_NAME
import br.com.arthur.githubapp.util.constants.Constants.KEY_REPOSITORY_NAME

class PullRequestActivity : BaseActivity() {

    private lateinit var bind: ActivityPullRequestBinding

    private lateinit var repositoryViewModel: RepositoryViewModel

    private var adapter = PullRequestAdapter()

    private lateinit var ownerName: String
    private lateinit var repositoryName: String
    private var openPull: Int = 0
    private var closedPull: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_pull_request)
    }

    override fun onResume() {
        super.onResume()
        configureData()
        showLoadingDialog()
        repositoryViewModel.requestPulls(ownerName, repositoryName)
    }

    override fun onDestroy() {
        repositoryViewModel.mutablePullRequestList.removeObservers(this)
        repositoryViewModel.mutableErrorPullMessage.removeObservers(this)
        super.onDestroy()
    }

    private fun configureData() {
        repositoryViewModel = RepositoryViewModel(this)
        ownerName = intent.getStringExtra(KEY_OWNER_NAME)
        repositoryName = intent.getStringExtra(KEY_REPOSITORY_NAME)
        configureComponents()
        configureObservers()
    }

    private fun configureComponents() {
        val layoutManager = LinearLayoutManager(this)
        bind.pullRecycler.layoutManager = layoutManager
        bind.pullRecycler.itemAnimator = DefaultItemAnimator()
        bind.pullRecycler.adapter = adapter

        bind.pullError.setOnClickListener {
            showLoadingDialog()
            repositoryViewModel.requestPulls(ownerName, repositoryName)
        }

        bind.backButton.setOnClickListener {
            finish()
        }
    }

    private fun configureObservers() {
        repositoryViewModel.mutablePullRequestList.observe(this, Observer { pulls ->
            dismissLoadingDialog()
            countStatePull(pulls)
            bind.pullRecycler.visibility = View.VISIBLE
            bind.pullError.visibility = View.INVISIBLE
            adapter.setPullList(pulls)
        })

        repositoryViewModel.mutableErrorPullMessage.observe(this, Observer { error ->
            dismissLoadingDialog()
            bind.pullRecycler.visibility = View.INVISIBLE
            bind.openPulls.visibility = View.INVISIBLE
            bind.closedPull.visibility = View.INVISIBLE
            bind.pullError.visibility = View.VISIBLE
            bind.pullError.text = error
        })
    }

    @SuppressLint("SetTextI18n")
    private fun countStatePull(pulls: List<PullRequest>) {
        bind.openPulls.visibility = View.VISIBLE
        bind.closedPull.visibility = View.VISIBLE
        for (pull in pulls) {
            if (pull.state == "open") {
                openPull++
            } else {
                closedPull++
            }
        }
        bind.pullRepositoryName.text = repositoryName
        bind.openPulls.text = "$openPull opened "
        bind.closedPull.text = "/ $closedPull closed "
    }
}
