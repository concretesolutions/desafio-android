package com.example.desafioandroid.viewModel.fragment

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
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
    var pullProgress: ObservableInt = ObservableInt(View.INVISIBLE)
    var recyclerPull: ObservableInt = ObservableInt(View.INVISIBLE)
    var messageLabel: ObservableField<String> = ObservableField("")
    var visibilityLabel: ObservableInt = ObservableInt(View.VISIBLE)
    var pullList: List<PullItem>? = null

    private lateinit var pullListLiveData: MutableLiveData<List<PullItem>>

    fun initializeViews() {
        recyclerPull.set(View.INVISIBLE)
        pullProgress.set(View.VISIBLE)
        visibilityLabel.set(View.VISIBLE)
    }

    fun fetchPullList(): LiveData<List<PullItem>>  {
        pullListLiveData = MutableLiveData()
        loadTeams()
        return pullListLiveData
    }

    private fun loadTeams(){
        val apiService = RetrofitClient.getClient().create(ApiInterface::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                apiService.getPull(creator!!,nameRepository!!).await().let {

                   if (it.isSuccessful){
                       pullProgress.set(View.INVISIBLE)
                       recyclerPull.set(View.VISIBLE)
                       visibilityLabel.set(View.INVISIBLE)
                       pullList = it.body()
                       pullListLiveData.value = it.body()

                   }else {
                       pullProgress.set(View.INVISIBLE)
                       recyclerPull.set(View.INVISIBLE)
                       visibilityLabel.set(View.VISIBLE)
                       messageLabel.set(it.errorBody().toString())
                       pullListLiveData.value = null
                   }
                }

            }catch (exception : Exception){
                Log.e(TAG, "Failed to fetch data!")
                pullProgress.set(View.INVISIBLE)
                recyclerPull.set(View.INVISIBLE)
                visibilityLabel.set(View.VISIBLE)
                messageLabel.set(exception.message)
                pullListLiveData.value = null

            }
        }


    }
}
