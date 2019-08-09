package sergio.com.br.desafioandroid.ui.view_models

import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sergio.com.br.desafioandroid.api.APIClient
import sergio.com.br.desafioandroid.models.PullRequestModel

class PullRequestViewModel : BaseViewModel() {
    val pullRequestMutableLiveData: MutableLiveData<ArrayList<PullRequestModel>> by lazy {
        MutableLiveData<ArrayList<PullRequestModel>>()
    }

    var pullRequestsList: ArrayList<PullRequestModel> = ArrayList()

    fun loadPullRequests(creator: String, repository: String) {
        isLoading.postValue(true)

        APIClient.apiInterface().loadPullRequests(creator, repository)
            .enqueue(object : Callback<ArrayList<PullRequestModel>> {
                override fun onFailure(call: Call<ArrayList<PullRequestModel>>?, t: Throwable) {
                    isLoading.postValue(false)
                    onThrowable.postValue(t.message)
                }

                override fun onResponse(
                    call: Call<ArrayList<PullRequestModel>>?,
                    response: Response<ArrayList<PullRequestModel>>?
                ) {
                    isLoading.postValue(false)

                    if (response != null) {
                        if (!response.isSuccessful) {
                            onError.postValue(response)
                            return
                        }

                        pullRequestsList.addAll(response.body())
                        pullRequestMutableLiveData.postValue(pullRequestsList)
                    } else {
                        onError.postValue(response)
                    }
                }

            })
    }
}