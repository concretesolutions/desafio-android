package br.com.henriqueoliveira.desafioandroidconcrete.service.repository


import androidx.lifecycle.MutableLiveData
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.ServerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryDataSource(var gitHubService: GitHubService) {

    val networkState = MutableLiveData<NetworkState>()

    fun getRepositories(page: Int, listener: ResultListener<ServerResponse>) {

        networkState.postValue(NetworkState.LOADING)

        gitHubService.getRepositoryList("java", page, "starts")
                .enqueue(object : Callback<ServerResponse> {
                    override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                        if (response.isSuccessful)
                            networkState.postValue(NetworkState.LOADED)
                        listener.onSuccess(response.body())

                    }

                    override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                        networkState.postValue(NetworkState.error(t.message ?: "unknown err"))
                    }
                })
    }

    fun getPullRequests(owner: String, repositoryName: String, listener: ResultListener<List<PullRequest>>) {

        gitHubService.getPullRequests(owner, repositoryName)
                .enqueue(object : Callback<List<PullRequest>> {
                    override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                        if (response.isSuccessful)
                            listener.onSuccess(response.body())
                    }

                    override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                        networkState.postValue(NetworkState.error(t.message ?: "unknown err"))
                    }
                })
    }

    fun exposeNetworkState(): MutableLiveData<NetworkState> {
        return networkState
    }


}