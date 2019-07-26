package com.example.challengecskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GithubApi.searchService.fetchAllUsers("language:java").enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                d("Erro", "onFailure: " + t.message)
            }
        })
    }

    private fun showData(repos: List<Repo>) {
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = RepoAdapter(repos)
        }
    }
}
