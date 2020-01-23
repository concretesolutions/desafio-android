package br.com.rmso.popularrepositories.ui.pullrequest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rmso.popularrepositories.utils.ListOnClickListener
import br.com.rmso.popularrepositories.R
import br.com.rmso.popularrepositories.model.PullRequest
import br.com.rmso.popularrepositories.utils.Constants
import kotlinx.android.synthetic.main.activity_pull_request.*

class PullRequestActivity : AppCompatActivity(),
    ListOnClickListener, IPullRequest.View{

    private var owner = ""
    private var repositoryName = ""
    private var pullRequestArrayList = ArrayList<PullRequest>()
    private val constans = Constants()
    var linearLayoutManager = LinearLayoutManager(this@PullRequestActivity)
    var adapterRepository = PullResquestAdapter(
        pullRequestArrayList,
        this@PullRequestActivity
    )
    private var pullRequestPresenter: IPullRequest.Presenter = PullRequestPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        owner = intent.getStringExtra(constans.owner)
        repositoryName = intent.getStringExtra(constans.repository)

        toolbar.title = repositoryName
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (savedInstanceState != null) {
            pullRequestArrayList.addAll(savedInstanceState.getParcelableArrayList(constans.listPullRequestInstance))
            owner = savedInstanceState.getString(constans.owner)
            repositoryName = savedInstanceState.getString(constans.repository)
        }else {
            (pullRequestPresenter as PullRequestPresenter).requestList(pullRequestArrayList, owner, repositoryName)
        }

        rv_pull_request.apply {
            layoutManager = linearLayoutManager
            adapter = adapterRepository
        }
    }

    override fun updateList(list: List<PullRequest>?, owner: String, repositoryName: String) {
        pullRequestArrayList.addAll(list!!)
        rv_pull_request.adapter?.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(constans.listPullRequestInstance, pullRequestArrayList)
        outState.putString(constans.owner, owner)
        outState.putString(constans.repository, repositoryName)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(position: Int) {
        val pullRequest = pullRequestArrayList[position]
        val url = pullRequest.html_url
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)

    }

    override fun progressBar(status: Boolean) {
        if(status) {
            pb_loading_pull.visibility = View.VISIBLE
        }else {
            pb_loading_pull.visibility = View.GONE
        }
    }

    override fun errorRequest(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
