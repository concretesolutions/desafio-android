package com.mbstro.fmoyagonzalez.desafio_android

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AbsListView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

/**
 * Esta clase contiene la lista de repositorios de java comenzando con la pagina 1
 */
class MainActivity : AppCompatActivity() {

    var repos  = arrayListOf<Repo>()
    private var pastVisiblesItems: Int = 0
    private var visibleItemCount:Int = 0
    private var totalItemCount:Int = 0
    private var page = 1
    private var isScrolling = false
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private val PAGE = "https://api.github.com/search/repositories?q=language:Java&sort=stars&page="
    private var viewManager =  LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.title_repo)
        setContentView(R.layout.activity_main)
        getHTTPVolley("$PAGE$page")
        viewAdapter = RepoAdapter(repos){
            Log.w("CLICKED", it.name)
            val intent = Intent(this, PullRequestActivity::class.java)
            intent.putExtra("REPO",it.name)
            intent.putExtra("AUTOR",it.owner.login)
            startActivity(intent)
        }
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                changePage()
            }
        })
    }


    private fun getHTTPVolley(url: String){
        val queue = VolleySingleton.getInstance(this.applicationContext).requestQueue
        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    val json = response.getJSONArray("items")
                    this.populateRepo(json)
                    viewAdapter.notifyDataSetChanged()
                    first_progressBar.visibility = View.INVISIBLE
                    botton_progressBar.visibility = View.INVISIBLE
                },
                Response.ErrorListener { error ->
                    Log.w("ERROR", error)
                }
        )
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        //queue.add(jsonObjectRequest)
    }
    private fun populateRepo(json: JSONArray){
        for(i in 0 until json.length()) {
            val gson = Gson()
            val item = gson.fromJson(json.getJSONObject(i).toString(), Repo::class.java)
            this.repos.add(item)
        }
    }
    private fun changePage(){
        visibleItemCount = viewManager.childCount
        totalItemCount = viewManager.itemCount
        pastVisiblesItems = viewManager.findFirstVisibleItemPosition()

        if (isScrolling && visibleItemCount + pastVisiblesItems == totalItemCount) {
            isScrolling = false
            page += 1
            botton_progressBar.visibility = View.VISIBLE
            getHTTPVolley("$PAGE$page")
        }
    }
}
