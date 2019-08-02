package wilquer.lima.desafioconcrete.ui.pullrequest

import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wilquer.lima.desafioconcrete.data.model.PullRequest
import wilquer.lima.desafioconcrete.network.RetrofitApi
import wilquer.lima.desafioconcrete.network.service.PullRequestService
import wilquer.lima.desafioconcrete.util.Constants

class PullRequestPresenter(val view: PullRequestContract.View) : PullRequestContract.Presenter {

    override fun getPullRequests(creator: String, repo: String) {
        view.setProgress(true)

        doAsync {
            val apiService = RetrofitApi(Constants.GENERAL_URL).client.create(PullRequestService::class.java)

            val call = apiService.getPullRequests(creator, repo)
            call.enqueue(object : Callback<List<PullRequest>>{
                override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                    view.setProgress(false)
                    view.error(t.message.toString())
                }

                override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                    view.setProgress(false)
                    view.pullrequests(response.body())
                }

            })
        }
    }


}