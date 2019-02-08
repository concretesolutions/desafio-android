package com.accenture.githubrepositories

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.util.Log
import android.widget.Toast
import com.accenture.githubrepositories.adapters.RepositoriesAdapter
import com.accenture.githubrepositories.utils.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main.*
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

const val BASE_REPOSITORIES_URL: String = "search/repositories?q=language:Java&sort=stars&"

class MainActivity : AppCompatActivity() {

    private lateinit var repositoriesAdapter: RepositoriesAdapter
    private var pageNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoriesAdapter = RepositoriesAdapter(this)
        recycler_main_repos_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler_main_repos_view.adapter = repositoriesAdapter

        //WE CHECK INTERNET CONNECTION
        if (verifyAvailableNetwork(this)) {
            progressBarRepoLoad.visibility = View.VISIBLE
            loadRepositoriesApiData(BASE_REPOSITORIES_URL + "page=" + pageNumber)
        } else {
            Toast.makeText(this, "Error no internet", Toast.LENGTH_SHORT).show()
        }

        //INFINITE SCROLL IMPLEMENTATION
        recycler_main_repos_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                if (dy > 0) {
                    // Recycle view scrolling down...
                    if (!recyclerView!!.canScrollVertically(RecyclerView.FOCUS_DOWN)) {

                        //WE CHECK INTERNET CONNECTION
                        if (verifyAvailableNetwork(this@MainActivity)) {
                            val visibility = if (progressBarRepoLoad.visibility == View.GONE) View.VISIBLE else View.GONE
                            progressBarRepoLoad.visibility = visibility
                            ++pageNumber
                            loadRepositoriesApiData(BASE_REPOSITORIES_URL + "page=" + pageNumber)
                        }
                        else{
                            Toast.makeText(applicationContext, "Error no internet", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }


        })


    }


    override fun onDestroy() {
        super.onDestroy()
        repositoriesAdapter.clearRepositories()
    }


    @SuppressLint("CheckResult")
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun loadRepositoriesApiData(url: String) {

        try {
            val retrofitClient = RetrofitClient.create()
            retrofitClient.getRepositories(url)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ it ->
                        repositoriesAdapter.setRepositories(it.items)
                        repositoriesAdapter.notifyDataSetChanged()
                        progressBarRepoLoad.visibility = View.GONE
                    },
                            {
                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                                Log.e("ERRRORRRRRRRRRR: ", it.message)
                                progressBarRepoLoad.visibility = View.GONE
                            })

        } catch (e: Exception) {
            throw e
        }
    }


    fun verifyAvailableNetwork(context: Context):Boolean{
        val connectivityManager= (context as MainActivity).getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

    }
