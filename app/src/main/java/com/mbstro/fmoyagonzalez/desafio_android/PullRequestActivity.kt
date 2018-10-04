package com.mbstro.fmoyagonzalez.desafio_android

import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.Gson
import android.content.Intent
import android.net.Uri


class PullRequestActivity : AppCompatActivity() {

    var pullRequestsList  = arrayListOf<PullRequest>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
        val autor = intent.getStringExtra("AUTOR")
        val repo = intent.getStringExtra("REPO")
        getHTTPVolley("https://api.github.com/repos/${autor}/${repo}/pulls")

    }

    private fun getHTTPVolley(url: String){

        Log.d("PAGE", url)
        // Get a RequestQueue
        val queue = VolleySingleton.getInstance(this.applicationContext).requestQueue
        // Request a string response from the provided URL.

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->

                    for(i in 0 until response.length()) {
                        val gson = Gson()
                        val item = gson.fromJson(response[i].toString(), PullRequest::class.java)
                        this.pullRequestsList.add(item)
                    }
                    val viewManager = LinearLayoutManager(this)
                    val viewAdapter = PullRequestAdapter(pullRequestsList){
                        Log.w("CLICKED", it.html_url)
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url))
                        startActivity(browserIntent)
                    }
                    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_pull_request).apply {
                        // use this setting to improve performance if you know that changes
                        // in content do not change the layout size of the RecyclerView
                        //setHasFixedSize(true)
                        // use a linear layout manager
                        layoutManager = viewManager
                        // specify an viewAdapter (see also next example)
                        adapter = viewAdapter

                    }

                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                    Log.w("ERROR","That didn't work!")
                }
        )
        // Add the request to the RequestQueue.
        VolleySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)
    }

}
