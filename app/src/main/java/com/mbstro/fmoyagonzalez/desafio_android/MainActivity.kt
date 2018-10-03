package com.mbstro.fmoyagonzalez.desafio_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var repos  = arrayListOf<Repo>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getHTTPVolley("https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1")
    }


    private fun getHTTPVolley(url: String){

        // Get a RequestQueue
        val queue = VolleySingleton.getInstance(this.applicationContext).requestQueue
        // Request a string response from the provided URL.
        first_progressBar.visibility = VISIBLE
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    //val repo = gson.fromJson(response.["items"][0], Repo::class.java)
                    val json = response.getJSONArray("items")
                    for(i in 0 until json.length()) {
                        val gson = Gson();
                        val item = gson.fromJson(json.getJSONObject(i).toString(), Repo::class.java)
                        this.repos.add(item)
                    }
                    val viewManager = LinearLayoutManager(this)
                    val viewAdapter = RepoAdapter(repos){
                        Log.w("CLICKED", it.name)
                        val intent = Intent(this, PullRequestActivity::class.java)
                        intent.putExtra("REPO",it.name)
                        intent.putExtra("AUTOR",it.owner.login)
                        startActivity(intent)
                    }
                    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
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
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        first_progressBar.visibility = GONE
    }


    fun onClick(v: View) {
        val id = v.getId()

    }
}
