package com.example.artul.concrete_desafio_android

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.example.artul.adapters.PullRequestAdapter
import com.example.artul.models.PullRequest
import com.example.artul.util.RetrofitInitializer
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestActivity : AppCompatActivity() {

    lateinit var listPull: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        val txtWarning: TextView = find<TextView>(R.id.txt_warning)

        listPull = find(R.id.list_pull) as ListView

        val repository = intent.getStringExtra("repository_name")
        val owner = intent.getStringExtra("owner")

        val call = RetrofitInitializer().pullService().list(owner, repository)

        call.enqueue(object: Callback<List<PullRequest>> {

            override fun onResponse(call: Call<List<PullRequest>>?, response: Response<List<PullRequest>>?) {

                response?.body()?.let {

                    val listPullRequest = it as ArrayList<PullRequest>

                    val adapter = PullRequestAdapter(applicationContext, listPullRequest)

                    if(listPullRequest.size == 0){

                        listPull.visibility = View.GONE
                        txtWarning.visibility = View.VISIBLE

                    } else {

                        listPull.visibility = View.VISIBLE
                        txtWarning.visibility = View.GONE

                    }

                    listPull.adapter = adapter

                    listPull.setOnItemClickListener{ adapterView, view, i, l ->

                        val pullRequest = adapter.getItem(i) as PullRequest

                        val intent: Intent = Intent(Intent.ACTION_VIEW)

                        intent.setData(Uri.parse(pullRequest.html_url))

                        startActivity(intent)

                    }

                }

            }

            override fun onFailure(call: Call<List<PullRequest>>?, t: Throwable?) {
                Log.d("Error Requisition", t?.message)
            }

        })

    }
}
