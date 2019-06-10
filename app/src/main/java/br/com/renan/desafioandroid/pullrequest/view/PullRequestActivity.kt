package br.com.renan.desafioandroid.pullrequest.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.renan.desafioandroid.R
import br.com.renan.desafioandroid.model.data.PullRequest
import br.com.renan.desafioandroid.model.data.PullRequestList
import br.com.renan.desafioandroid.pullrequest.presentation.IPullRequestContract
import br.com.renan.desafioandroid.pullrequest.presentation.PullRequestPresenter
import kotlinx.android.synthetic.main.activity_pull_request.*

class PullRequestActivity : AppCompatActivity(), IPullRequestContract.View {

    private val pullRequestPresenter = PullRequestPresenter()
    private lateinit var pullRequestAdapter: PullRequestAdapter
    private val listPullRequest = ArrayList<PullRequest>()

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        pullRequestPresenter.bind(this)

        pullRequestPresenter.requestPullRequestData("ReactiveX","RxJava")


        pullRequestAdapter = PullRequestAdapter(listPullRequest)
        pullRequestRecycler.itemAnimator = DefaultItemAnimator()
        linearLayoutManager = LinearLayoutManager(this)
        pullRequestRecycler.layoutManager = linearLayoutManager
        pullRequestRecycler.adapter = pullRequestAdapter
    }


    override fun pullRequestSuccess(pullRequestList: PullRequestList) {
        listPullRequest.addAll(pullRequestList.pullRequestList)
        pullRequestAdapter.notifyDataSetChanged()
    }

    override fun showPullRequestLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPullRequestError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPullRequestSucess() {
        pullRequestRecycler.visibility = View.VISIBLE
    }
}
