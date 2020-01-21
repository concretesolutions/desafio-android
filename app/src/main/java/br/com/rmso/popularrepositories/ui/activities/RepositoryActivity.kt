package br.com.rmso.popularrepositories.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rmso.popularrepositories.R
import br.com.rmso.popularrepositories.model.RepositoryListCallback
import br.com.rmso.popularrepositories.retrofit.RetrofitAPI
import br.com.rmso.popularrepositories.ui.adapters.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryActivity : AppCompatActivity() {

    var retrofit = RetrofitAPI()
    var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        val callRespository = retrofit.repositoryService.listRepositories(count)

        callRespository.enqueue(object : Callback<RepositoryListCallback> {
            override fun onResponse(call: Call<RepositoryListCallback>, response: Response<RepositoryListCallback>) {
                val listRepositories = response.body()!!
                configureListRepository(listRepositories)
            }

            override fun onFailure(call: Call<RepositoryListCallback>, t: Throwable) {
                Log.e("onFailure error", t.message)
            }
        })
    }

    fun configureListRepository (list: RepositoryListCallback){
        val listRepositories = list.items
        rv_repository.apply {
            layoutManager = LinearLayoutManager(this@RepositoryActivity)
            adapter = RepositoryAdapter(listRepositories)
        }

    }
}
