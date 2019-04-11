package com.example.desafioandroid.viewModel.fragment

import android.content.Context
import android.util.Log
import android.view.View
import androidx.annotation.UiThread
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioandroid.R
import com.example.desafioandroid.api.ApiInterface
import com.example.desafioandroid.api.RetrofitClient
import com.example.desafioandroid.schema.PullItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PullViewModel(private var context: Context, private var creator: String, private var nameRepository:String): ViewModel() {
    val TAG = javaClass.simpleName

    private var pullProcess: ObservableInt = ObservableInt(View.GONE)
    private var recyclerPull: ObservableInt = ObservableInt(View.GONE)
    private var labelStatus: ObservableInt = ObservableInt(View.VISIBLE)
    private var messageLabel: ObservableField<String> = ObservableField(context.getString(R.string.app_name))

    private lateinit var pullList: MutableLiveData<List<PullItem>>

    fun initializeViews() {
        labelStatus.set(View.GONE)
        recyclerPull.set(View.GONE)
        pullProcess.set(View.VISIBLE)
        fetchPullList()
    }

    @UiThread
    fun fetchPullList(): LiveData<List<PullItem>> {
        val apiService = RetrofitClient.getClient().create(ApiInterface::class.java)
        if (::pullList.isInitialized) {
            pullList = MutableLiveData()
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = apiService.getPull(creator,nameRepository).await()
                    when{

                        response.isSuccessful ->{
                            val liveData = MutableLiveData<List<PullItem>>()
                            liveData.value = response.body()

                            pullList = liveData
                            pullProcess.set(View.GONE)
                            labelStatus.set(View.GONE)
                            recyclerPull.set(View.VISIBLE)
                        }else ->{
                        messageLabel.set(context.getString(R.string.app_name))
                        pullProcess.set(View.GONE)
                        labelStatus.set(View.VISIBLE)
                        recyclerPull.set(View.GONE)
                    }
                    }
                }catch (exception : Exception){
                    Log.e(TAG, "Failed to fetch data!")
                }
            }
        }
        return pullList
    }

    fun goneView() {
        labelStatus.set(View.GONE)
        recyclerPull.set(View.VISIBLE)
        pullProcess.set(View.GONE)
    }

}
