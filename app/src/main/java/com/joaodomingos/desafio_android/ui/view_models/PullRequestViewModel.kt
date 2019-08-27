package com.joaodomingos.desafio_android.ui.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.joaodomingos.desafio_android.api.NetworkService
import com.joaodomingos.desafio_android.api.State
import com.joaodomingos.desafio_android.models.PullRequestModel
//import com.joaodomingos.desafio_android.ui.data_source.PullRequestDataSource
//import com.joaodomingos.desafio_android.ui.factory.PullRequestDataSourceFactory
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestViewModel: ViewModel() {
    val TAG = this.javaClass
    val pullRequestMutableLiveData: MutableLiveData<List<PullRequestModel>> by lazy {
        MutableLiveData<List<PullRequestModel>>()
    }

    var pullRequestsList: ArrayList<PullRequestModel> = ArrayList()

    fun loadPullRequests(creator: String, repository: String) {
        State.LOADING

        NetworkService.getService().getPullRequestListItens(creator, repository)
            .enqueue(object : Callback<List<PullRequestModel>> {
                override fun onFailure(call: Call<List<PullRequestModel>>?, t: Throwable) {
                    State.ERROR
                }

                override fun onResponse(
                    call: Call<List<PullRequestModel>>?,
                    response: Response<List<PullRequestModel>>?
                ) {
                    State.DONE

                    if (response != null) {
                        if (!response.isSuccessful) {
                            State.ERROR
                            return
                        }

                        response.body()?.let { pullRequestsList.addAll(it) }
                        pullRequestMutableLiveData.postValue(pullRequestsList)
                    } else {
                        State.ERROR
                    }
                }

            })
    }
}