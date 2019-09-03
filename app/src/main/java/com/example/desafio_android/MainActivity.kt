package com.example.desafio_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio_android.adapter.AdapterListRepositories
import com.example.desafio_android.api.GitHubAPIinterface
import com.example.desafio_android.api.GitHubAPIservice
import com.example.desafio_android.listeners.RepositoriesScrollListener
import com.example.desafio_android.pojo.Item
import com.example.desafio_android.pojo.Repositories
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var repositories: Repositories? = null
    var itensRepositories = ArrayList<Item>()
    lateinit var mAdapter: AdapterListRepositories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = AdapterListRepositories(itensRepositories, this)
        lista.adapter = mAdapter

        lista.setOnScrollListener(object : RepositoriesScrollListener() {
            override fun onLoadMore(page: Int, totalItemsCount: Int): Boolean {
                getRepositories(page)
                return true
            }
        })
        getRepositories(1)
    }

    private fun getRepositories(page: Int) {
        val apiInterface = GitHubAPIservice.getClient().create(GitHubAPIinterface::class.java)

        val callws = apiInterface.getRepositories(page)

        callws.enqueue(object : Callback<Repositories> {
            override fun onResponse(call: Call<Repositories>?, response: Response<Repositories>?) {
                val resource = response?.body()
                if (resource != null) {
                    repositories = resource
                    itensRepositories = repositories?.items as ArrayList<Item>
                    mAdapter.setItens(itensRepositories)
                    mAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Repositories>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}
