package com.example.challengecskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_pull_request.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class PullRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
        getIncomingIntent()
    }

    private fun getIncomingIntent(){
        d("Second Activity", "recebendo intent")
        if(intent.hasExtra("login") && intent.hasExtra("repositoryName")){
            val login = intent.getStringExtra("login")
            val repositoryName = intent.getStringExtra("repositoryName")
            d("SecondActivity", "login clicado: $login")
            getPullRequests(login, repositoryName)
            title = repositoryName
        }
    }

    private fun getPullRequests(login: String, name: String){
        GithubApi.searchService.fetchPullRequests(login, name).enqueue(object : Callback<List<PullRequestObject>> {
            override fun onResponse(call: Call<List<PullRequestObject>>, response: Response<List<PullRequestObject>>) {
                showData(response.body()!!)
                d("onResponse", "response clicked: " + response.body())
            }

            override fun onFailure(call: Call<List<PullRequestObject>>, t: Throwable) {
                d("onFailure", "fail: " + t.message)
            }
        })
    }

    private fun showData(pullRequests: List<PullRequestObject>) {
        rv_pull_requests.apply{
            layoutManager = LinearLayoutManager(this@PullRequestActivity)
            adapter = PullRequestAdapter(pullRequests, this@PullRequestActivity)
        }
    }

}
