package com.ccortez.desafioconcreteapplication.service.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ccortez.desafioconcreteapplication.service.model.PullRequest
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepository {

    private var backEndService: BackEndService? = null
    var mContext: Context? = null

    @Inject
    constructor(backEndService: BackEndService?) {
        this.backEndService = backEndService
    }

    constructor() {}

    fun repositories(): LiveData<Repositories> {
        val data =
            MutableLiveData<Repositories>()
        backEndService?.repositories()
            ?.enqueue(object : Callback<Repositories> {
                override fun onResponse(
                    call: Call<Repositories>,
                    response: Response<Repositories>
                ) {
                    Log.d(TAG, "repositories: " + response.body())
                    data.value = response.body()
                }

                override fun onFailure(
                    call: Call<Repositories>,
                    t: Throwable
                ) {
                    Log.e(TAG, "error repositories: ", t)
                    // TODO better error handling in part #2 ...
                    data.value = null
                }
            })
        return data
    }

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