package cl.carteaga.querygithub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import cl.carteaga.querygithub.adapters.AdapterPullRequest
import cl.carteaga.querygithub.apis.GitHubApi
import cl.carteaga.querygithub.apis.GitHubEndPoints
import cl.carteaga.querygithub.models.PullRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterPullRequest: AdapterPullRequest
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var progressBar: ProgressBar
    private var pullRequestService: GitHubEndPoints? = null
    private var nameRepository: String = ""
    private var nameAuthorRepository: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        val extras: Bundle = this.intent.extras
        nameRepository = extras["nameRepository"] as String
        nameAuthorRepository = extras["authorName"] as String

        this.title = nameRepository
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progressBar = findViewById(R.id.progressBarPullRequest)
        recyclerView = findViewById(R.id.rcPullRequest)
        recyclerView.setHasFixedSize(true)
        viewManager = LinearLayoutManager(this)
        recyclerView.layoutManager = viewManager
        pullRequestService = GitHubApi.getClient()?.create(GitHubEndPoints::class.java)
        adapterPullRequest = AdapterPullRequest(mutableListOf())
        recyclerView.adapter = adapterPullRequest
        loadPage()
    }

    private fun loadPage() {
        progressBar.visibility = View.VISIBLE
        callPullRequestService()?.enqueue(object: Callback<List<PullRequest>> {
            override fun onFailure(call: Call<List<PullRequest>>,t: Throwable) {
            }

            override fun onResponse(call: Call<List<PullRequest>>,
                                    response: Response<List<PullRequest>>
            ) {
                if(response.code() == 200) {
                    adapterPullRequest = AdapterPullRequest(response.body())
                    recyclerView.adapter = adapterPullRequest
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun callPullRequestService(): Call<List<PullRequest>>? {
        return pullRequestService?.getPullRequestRepository(nameAuthorRepository, nameRepository)
    }
}
