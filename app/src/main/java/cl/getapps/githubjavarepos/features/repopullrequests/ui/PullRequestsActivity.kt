package cl.getapps.githubjavarepos.features.repopullrequests.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cl.getapps.githubjavarepos.R
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestParams
import cl.getapps.githubjavarepos.features.repopullrequests.domain.model.PullRequestModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.android.viewmodel.ext.android.viewModel


class PullRequestsActivity : AppCompatActivity() {

    private var pageParam: Int = 1
    private var loadingFromServer: Boolean = false

    private val pullRequestsRecyclerViewAdapter: PullRequestsRecyclerViewAdapter by inject()
    private var linearLayoutManager: LinearLayoutManager? = null

    private val pullRequestsViewModel: PullRequestsViewModel by viewModel()

    private var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        bindScope(getOrCreateScope("PullRequests"))

        setToolbar()

        makeSnackBar()

        setupRecyclerView()

        setupViewModel()

        loadPullRequests()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.title = "$title ${ARGS.REPO_NAME}"
    }

    private fun makeSnackBar() {
        snackBar = Snackbar.make(item_list, "Loading items ...", Snackbar.LENGTH_INDEFINITE)
    }

    private fun setupRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        item_list.layoutManager = linearLayoutManager
        item_list.adapter = pullRequestsRecyclerViewAdapter
    }

    private fun setupViewModel() {
        pullRequestsViewModel.getPullRequestsLiveData()
            .observe(this, Observer<MutableList<PullRequestModel>> { pullRequests ->
                if (pullRequests.isNotEmpty() && pullRequests[0].title != "Error") setRecyclerViewData(pullRequests)
                else if (pullRequests[0].title == "Error") showSnackBar("Error loading items :(", isError = true)
            })
    }

    private fun loadPullRequests() = if (!loadingFromServer) {
        loadingFromServer = true
        pageParam++
        pullRequestsViewModel.fetchPullRequests(
            PullRequestParams(
                intent.getStringExtra(ARGS.REPO_OWNER),
                intent.getStringExtra(ARGS.REPO_NAME)
            )
        )
        showSnackBar("Loading items...", isError = false)
    } else {
        showSnackBar("Loading more items...", isError = false)
    }

    private fun showSnackBar(message: String, isError: Boolean) {
        snackBar?.setText(message)?.setDuration(BaseTransientBottomBar.LENGTH_INDEFINITE)?.run {
            if (isError) setAction("Retry") {
                loadPullRequests()
            }.show() else show()
        }
    }

    private fun setRecyclerViewData(repos: MutableList<PullRequestModel>) {
        pullRequestsRecyclerViewAdapter.values.addAll(repos)
        pullRequestsRecyclerViewAdapter.notifyDataSetChanged()
        loadingFromServer = false
        snackBar?.dismiss()
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    object ARGS {
        const val REPO_OWNER = "owner"
        const val REPO_NAME = "name"
    }
}
