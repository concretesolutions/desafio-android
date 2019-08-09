package sergio.com.br.desafioandroid.ui.activities.pull_request

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import sergio.com.br.desafioandroid.R
import sergio.com.br.desafioandroid.app.Constants.END_POINTS.PULL_REQUEST_HTML_URL
import sergio.com.br.desafioandroid.listeners.PullRequestListListener
import sergio.com.br.desafioandroid.models.PullRequestModel
import sergio.com.br.desafioandroid.ui.activities.BaseActivity
import sergio.com.br.desafioandroid.ui.adapters.PullRequestRecycleAdapter
import sergio.com.br.desafioandroid.ui.view_models.PullRequestViewModel
import sergio.com.br.desafioandroid.utils.Utils

class PullRequestActivity : BaseActivity(), PullRequestListListener {
    internal lateinit var pullRequestViewModel: PullRequestViewModel

    lateinit var ownerName: String
    lateinit var repositoryName: String
    lateinit var adapter: PullRequestRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pullrequest)
        init()
        setCustomBar(repositoryName, true)
    }

    override fun populateData() {
        ownerName = intent.getStringExtra("ownerName")
        repositoryName = intent.getStringExtra("repositoryName")

        pullRequestViewModel = ViewModelProviders.of(this).get(PullRequestViewModel::class.java)
        addAPIObservables(pullRequestViewModel)

        if (!intent.getBooleanExtra("isTesting", false) && !pullRequestViewModel.hasLoaded) {
            pullRequestViewModel.hasLoaded = true
            pullRequestViewModel.loadPullRequests(ownerName, repositoryName)
        }

        pullRequestViewModel.pullRequestMutableLiveData.observe(this, Observer {
            if (it == null) {
                Utils.showSimpleMessage(
                    this,
                    getString(R.string.api_error_text),
                    getString(R.string.unexpected_api_error_text)
                )
            } else if (!::adapter.isInitialized) {
                setRecycleListView(it)
            }
        })
    }

    private fun setRecycleListView(pullRequestList: ArrayList<PullRequestModel>) {
        adapter = PullRequestRecycleAdapter(pullRequestList, this, this)

        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(adapter)
    }

    override fun onItemCliked(item: PullRequestModel) {
        val openURL = Intent(Intent.ACTION_VIEW)

        val url: String = PULL_REQUEST_HTML_URL.replace("{creator}", ownerName)
            .replace("{repository}", repositoryName)
            .replace("{number}", item.number.toString())

        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }
}