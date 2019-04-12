package com.example.desafioandroid.viewModel.fragment

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioandroid.api.ApiInterface
import com.example.desafioandroid.api.RetrofitClient
import com.example.desafioandroid.schema.PullItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PullViewModel: ViewModel() {
    val TAG = javaClass.simpleName

    var context : Context? = null
    var creator : String? = ""
    var nameRepository : String? = ""
    var pullProcess: ObservableInt = ObservableInt(View.GONE)
    var recyclerPull: ObservableInt = ObservableInt(View.GONE)
    var labelStatus: ObservableInt = ObservableInt(View.VISIBLE)

    var pullList: List<PullItem>? = null

    private lateinit var pullListLiveData: MutableLiveData<List<PullItem>>

    fun initializeViews() {
        labelStatus.set(View.GONE)
        recyclerPull.set(View.GONE)
        pullProcess.set(View.VISIBLE)
        fetchPullList()
    }

    fun fetchPullList(): LiveData<List<PullItem>>  {
        pullListLiveData = MutableLiveData()
        loadTeams()
        return pullListLiveData
    }

    fun loadTeams(){
        val apiService = RetrofitClient.getClient().create(ApiInterface::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                apiService.getPull(creator!!,nameRepository!!).await().let {
                    pullList = it.body()
                    pullListLiveData.value = it.body()
                }

            }catch (exception : Exception){
                Log.e(TAG, "Failed to fetch data!")

                pullProcess.set(View.GONE)
                labelStatus.set(View.VISIBLE)
                recyclerPull.set(View.GONE)
            }
        }


    }

    fun goneView() {
        labelStatus.set(View.GONE)
        recyclerPull.set(View.VISIBLE)
        pullProcess.set(View.GONE)
    }

}
