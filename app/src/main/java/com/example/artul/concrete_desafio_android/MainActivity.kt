package com.example.artul.concrete_desafio_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.artul.adapters.RepositoryAdapter
import com.example.artul.models.Generic
import com.example.artul.models.Repository
import com.example.artul.util.EndlessScrollListener
import com.example.artul.util.RetrofitInitializer
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var listRepository: ArrayList<Repository>
    lateinit var adapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listRepository = ArrayList<Repository>()

        listView = find(R.id.list_repository) as ListView

        adapter = RepositoryAdapter(applicationContext, listRepository)

        listView.adapter = adapter

        callAPI(1)

        listView.setOnScrollListener(object : EndlessScrollListener(){

            override fun onLoadMore(page: Int, totalItemCount: Int): Boolean {

                Log.d("#######################", page.toString())

                callAPI(page)

                return true

            }

        })

    }

    fun callAPI(index: Int){

        val call = RetrofitInitializer().repositoryService().list(index)

        call.enqueue(object: Callback<Generic> {

            override fun onResponse(call: Call<Generic>, response: Response<Generic>) {

                response?.body()?.let{

                    val generic = it

                    listRepository.addAll(generic.items)

                    adapter.notifyDataSetChanged()

                    listView.setOnItemClickListener{ adapterView, view, i, l ->

                        val repository = adapter.getItem(i) as Repository

                        startActivity<PullRequestActivity>("repository_name" to repository.name,
                                "owner" to repository.owner.login)

                    }

                }

            }

            override fun onFailure(call: Call<Generic>, t: Throwable?) {

                Log.d("Error Requisition", t?.message)

            }

        })

    }

}
