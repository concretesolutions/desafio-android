package br.com.rmso.popularrepositories.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rmso.popularrepositories.ListOnClickListener
import br.com.rmso.popularrepositories.R
import br.com.rmso.popularrepositories.model.PullRequest
import br.com.rmso.popularrepositories.retrofit.RetrofitAPI
import br.com.rmso.popularrepositories.ui.adapters.PullResquestAdapter
import kotlinx.android.synthetic.main.activity_pull_request.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestActivity : AppCompatActivity(), ListOnClickListener {
    var retrofit = RetrofitAPI()
    var owner = ""
    var repositoryName = ""
    var pullRequestArrayList = ArrayList<PullRequest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        owner = intent.getStringExtra("owner")
        repositoryName = intent.getStringExtra("repository")

        val callRespository = retrofit.pullRequestService.listPullRequests(owner, repositoryName)
        callRespository.enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                val listPullRequest = response.body()!!
                pullRequestArrayList.addAll(listPullRequest)
                configureListRepository(pullRequestArrayList)
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                Log.e("onFailure error", t.message)
            }
        })
    }


    fun configureListRepository (list: ArrayList<PullRequest>){
        rv_pull_request.apply {
            layoutManager = LinearLayoutManager(this@PullRequestActivity)
            adapter = PullResquestAdapter(list, this@PullRequestActivity)
        }
    }

    override fun onClick(position: Int) {
    }

}
