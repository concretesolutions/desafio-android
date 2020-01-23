package br.com.rmso.popularrepositories.ui.activities

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rmso.popularrepositories.ListOnClickListener
import br.com.rmso.popularrepositories.R
import br.com.rmso.popularrepositories.model.PullRequest
import br.com.rmso.popularrepositories.retrofit.RetrofitAPI
import br.com.rmso.popularrepositories.ui.adapters.PullResquestAdapter
import br.com.rmso.popularrepositories.utils.Constants
import kotlinx.android.synthetic.main.activity_pull_request.*
import kotlinx.android.synthetic.main.activity_repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestActivity : AppCompatActivity(), ListOnClickListener {
    private var retrofit = RetrofitAPI()
    private var owner = ""
    private var repositoryName = ""
    private var pullRequestArrayList = ArrayList<PullRequest>()
    private val constans = Constants()
    var linearLayoutManager = LinearLayoutManager(this@PullRequestActivity)
    var adapterRepository = PullResquestAdapter(pullRequestArrayList, this@PullRequestActivity)

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
            requestPull()
        }

        rv_pull_request.apply {
            layoutManager = linearLayoutManager
            adapter = adapterRepository
            updateList()
        }
    }

    private fun updateList() {
        rv_pull_request.adapter?.notifyDataSetChanged()
    }

    private fun requestPull (){
        setProgressBar(true)
        val callRespository = retrofit.pullRequestService.listPullRequests(owner, repositoryName)
        callRespository.enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                val listPullRequest = response.body()!!
                setProgressBar(false)
                pullRequestArrayList.addAll(listPullRequest)
                updateList()
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                Log.e(constans.msgError, t.message)
                setProgressBar(false)
            }
        })
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

    fun setProgressBar(status: Boolean){
        if(status) {
            pb_loading_pull.visibility = View.VISIBLE
        }else {
            pb_loading_pull.visibility = View.GONE
        }
    }

}
