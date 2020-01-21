package br.com.rmso.popularrepositories.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rmso.popularrepositories.ListOnClickListener
import br.com.rmso.popularrepositories.R
import br.com.rmso.popularrepositories.model.Repository
import br.com.rmso.popularrepositories.model.RepositoryListCallback
import br.com.rmso.popularrepositories.retrofit.RetrofitAPI
import br.com.rmso.popularrepositories.ui.adapters.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryActivity : AppCompatActivity(), ListOnClickListener {
    var retrofit = RetrofitAPI()
    var count = 1
    var repositoriesArrayList = ArrayList<Repository>()
    var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        requestList()
    }

    fun configureListRepository (list: ArrayList<Repository>) {
        rv_repository.apply {
            layoutManager = LinearLayoutManager(this@RepositoryActivity)
            adapter = RepositoryAdapter(list, this@RepositoryActivity)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastCompleteItem = linearLayoutManager.findLastCompletelyVisibleItemPosition()

                    if (!isLoading) {
                        if (lastCompleteItem == repositoriesArrayList.size - 1){
                            requestList()
                            isLoading = true
                        }
                    }

                }
            })
    }
}

    fun requestList() {
        val callRespository = retrofit.repositoryService.listRepositories(count)

        callRespository.enqueue(object : Callback<RepositoryListCallback> {
            override fun onResponse(call: Call<RepositoryListCallback>, response: Response<RepositoryListCallback>) {
                val listRepositories = response.body()!!
                repositoriesArrayList.addAll(listRepositories.items)
                configureListRepository(repositoriesArrayList)
            }

            override fun onFailure(call: Call<RepositoryListCallback>, t: Throwable) {
                Log.e("onFailure error", t.message)
            }
        })

        isLoading = false
    }

    override fun onClick(position: Int) {
        val intent = Intent(this@RepositoryActivity, PullRequestActivity::class.java)
        var repository = repositoriesArrayList[position]
        intent.putExtra("owner", repository.owner.login)
        intent.putExtra("repository", repository.name)
        startActivity(intent)
    }

}
