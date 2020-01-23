package br.com.rmso.popularrepositories.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rmso.popularrepositories.ListOnClickListener
import br.com.rmso.popularrepositories.R
import br.com.rmso.popularrepositories.model.Repository
import br.com.rmso.popularrepositories.model.RepositoryListCallback
import br.com.rmso.popularrepositories.retrofit.RetrofitAPI
import br.com.rmso.popularrepositories.ui.adapters.RepositoryAdapter
import br.com.rmso.popularrepositories.utils.Constants
import kotlinx.android.synthetic.main.activity_repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryActivity : AppCompatActivity(), ListOnClickListener {
    private var retrofit = RetrofitAPI()
    private var page = 1
    private var repositoriesArrayList = ArrayList<Repository>()
    private var isLoading = false
    private var lastPosition = 0
    private val constants = Constants()

    var linearLayoutManager = LinearLayoutManager(this@RepositoryActivity)
    var adapterRepository = RepositoryAdapter(repositoriesArrayList, this@RepositoryActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        requestList()

        rv_repository.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = adapterRepository

            updateList(repositoriesArrayList,lastPosition)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastCompleteItem = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                    if (!isLoading) {
                        if (lastCompleteItem == repositoriesArrayList.size - 1){
                            page += 1
                            isLoading = true
                            requestList()
                        }
                    }
                }
            })
        }
    }

    private fun updateList(list: ArrayList<Repository>, lastPosition: Int){
        if (page > 1) {
            rv_repository.adapter?.notifyItemRangeInserted(lastPosition, list.size)
        }else {
            rv_repository.adapter?.notifyDataSetChanged()
        }
        isLoading = false
    }

    fun requestList() {
        setProgressBar(true)
        val callRespository = retrofit.repositoryService.listRepositories(page)
        callRespository.enqueue(object : Callback<RepositoryListCallback> {
            override fun onResponse(call: Call<RepositoryListCallback>, response: Response<RepositoryListCallback>) {
                val listRepositories = response.body()!!
                setProgressBar(false)
                lastPosition = repositoriesArrayList.size + 1
                repositoriesArrayList.addAll(listRepositories.items)
                updateList(repositoriesArrayList,lastPosition)

            }

            override fun onFailure(call: Call<RepositoryListCallback>, t: Throwable) {
                Log.e(constants.msgError, t.message)
                setProgressBar(false)
            }
        })
    }

    override fun onClick(position: Int) {
        val intent = Intent(this@RepositoryActivity, PullRequestActivity::class.java)
        val repository = repositoriesArrayList[position]
        intent.putExtra(constants.owner, repository.owner.login)
        intent.putExtra(constants.repository, repository.name)
        startActivity(intent)
    }

    fun setProgressBar(status: Boolean){
        if(status) {
            pb_loading.visibility = View.VISIBLE
        }else {
            pb_loading.visibility = View.GONE
        }
    }
}
