package br.com.rmso.popularrepositories.ui.pullrequest

import br.com.rmso.popularrepositories.model.PullRequest
import br.com.rmso.popularrepositories.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestPresenter (val view: IPullRequest.View) : IPullRequest.Presenter {

    override fun requestList(list: List<PullRequest>?, owner: String, repositoryName: String) {
        view.progressBar(true)
        val retrofit = RetrofitAPI()
        val callRespository = retrofit.pullRequestService.listPullRequests(owner, repositoryName)
        callRespository.enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                val listPullRequest = response.body()
                view.progressBar(false)
                view.updateList(listPullRequest, owner, repositoryName)
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                view.errorRequest(t.message.toString())
                view.progressBar(false)
            }
        })
    }
}