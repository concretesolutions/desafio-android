package com.concretesolutions.desafioandroid.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.concretesolutions.desafioandroid.R
import com.concretesolutions.desafioandroid.helpers.NetworkHelper
import com.concretesolutions.desafioandroid.model.Repositories
import com.concretesolutions.desafioandroid.service.RepositoryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("Repos", "começou")
        val repositories = NetworkHelper.getRetrofitInstanceGitHub()
            .create(RepositoryService::class.java)
            .getRepositories(1)

        repositories.enqueue(object : Callback<Repositories> {
            override fun onFailure(call: Call<Repositories>, t: Throwable) {
                Log.e("Repos", t.message)
            }
            override fun onResponse(call: Call<Repositories>, response: Response<Repositories>) {
                Log.i("Repos", "Aí porra!")
            }
        })
    }
}
