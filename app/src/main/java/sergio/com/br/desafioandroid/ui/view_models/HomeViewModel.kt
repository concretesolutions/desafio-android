package sergio.com.br.desafioandroid.ui.view_models

import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sergio.com.br.desafioandroid.api.APIClient
import sergio.com.br.desafioandroid.models.GitItemsModel
import sergio.com.br.desafioandroid.models.SearchListModel

class HomeViewModel : BaseViewModel() {
    val searchMutableLiveData: MutableLiveData<ArrayList<GitItemsModel>> by lazy {
        MutableLiveData<ArrayList<GitItemsModel>>()
    }

    var hasStoppedPaging: Boolean = false

    private var page: Int = 1
    private var searchList: ArrayList<GitItemsModel> = ArrayList()

    fun loadSearchList() {
        isLoading.postValue(true)

        APIClient.apiInterface().loadCharacters(page).enqueue(object : Callback<SearchListModel> {
            override fun onFailure(call: Call<SearchListModel>?, t: Throwable) {
                isLoading.postValue(false)
                onThrowable.postValue(t.message)
            }

            override fun onResponse(
                call: Call<SearchListModel>?,
                response: Response<SearchListModel>?
            ) {
                isLoading.postValue(false)

                if (response != null) {
                    if (!response.isSuccessful) {
                        onError.postValue(response)
                        return
                    }
                    searchList.addAll(response.body().itemsList)
                    searchMutableLiveData.postValue(searchList)
                } else {
                    onError.postValue(response)
                }
            }
        })
    }
}