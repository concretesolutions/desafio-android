package com.anderson.apigithub_mvvm.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.anderson.apigithub_mvvm.data.response.ItemResponse
import com.anderson.apigithub_mvvm.data.response.RepositoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by anderson on 21/09/19.
 */
class MainRepository @Inject constructor(val service: GitHutService) {

    private var q = "language:Java"
    private var sort = "stars"

    fun getListRepository(page : Int): MutableLiveData<ItemResponse> {
        val data = MutableLiveData<ItemResponse>()

        service.getListRepository(q,sort,page).enqueue(object : Callback<ItemResponse> {

            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                Log.e("Sucesso", response.body().toString())
                if(response.body() != null){
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.e("CALLBACK", t.message)
            }

        })

        return data
    }
}