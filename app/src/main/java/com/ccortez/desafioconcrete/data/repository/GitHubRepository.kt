package com.ccortez.desafioconcrete.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ccortez.desafioconcrete.data.remote.ApiService
import com.ccortez.desafioconcrete.model.PullRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepository {

    private var backEndService: ApiService? = null
    var mContext: Context? = null

    @Inject
    constructor(backEndService: ApiService?) {
        this.backEndService = backEndService
    }

    constructor() {}

    fun getPulls(carID: String?): LiveData<List<PullRequest>?> {
        Log.d(TAG, "full_name: "+ carID.toString())
        val data = MutableLiveData<List<PullRequest>?>()
        backEndService!!.getRepositoryPulls(carID)!!.enqueue(object : Callback<List<PullRequest>?> {
            override fun onResponse(
                call: Call<List<PullRequest>?>,
                response: Response<List<PullRequest>?>
            ) {
                simulateDelay()
                data.value = response.body()
            }

            override fun onFailure(
                call: Call<List<PullRequest>?>,
                t: Throwable
            ) { Log.e(TAG, "error getRepositoryPulls: ", t)
                // TODO better error handling in part #2 ...
                data.value = null
            }
        })
        return data
    }

    private fun simulateDelay() {
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }


    companion object {
        private val TAG = GitHubRepository::class.java.simpleName
    }
}