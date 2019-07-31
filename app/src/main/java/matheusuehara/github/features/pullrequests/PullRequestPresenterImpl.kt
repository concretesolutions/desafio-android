package matheusuehara.github.features.pullrequests

import android.util.Log
import matheusuehara.github.api.GitHubService
import matheusuehara.github.contract.GitHubContract
import matheusuehara.github.contract.PullRequestContract
import matheusuehara.github.model.PullRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class PullRequestPresenterImpl constructor(var view: PullRequestContract.View) : PullRequestContract.Presenter{

    private var service: GitHubContract.Service = GitHubService()

    override fun getPullRequests(owner:String,repository:String,status:String) {
        view.showProgressBar()
        service.getAPI().getPullRequests(owner,repository,status).enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                if (response.code() == 200) {
                    val allPullRequests = response.body() as ArrayList<PullRequest>
                    getPullRequestSuccess(allPullRequests)
                } else {
                    getPullRequestError()
                }
            }
            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                handleError(t)
            }
        })
    }

    private fun handleError(e:Throwable){
        Log.e("Error", e.message)
        view.hideProgressBar()
        view.showNetworkError()
    }

    override fun getPullRequestSuccess(pullRequestResult: ArrayList<PullRequest>?) {
        view.hideProgressBar()
        pullRequestResult?.let { prs ->
            val closedPr = prs.count{ it.state == "closed" }
            val openPr = prs.count{ it.state == "open" }
            view.updateStatus("$openPr Opened / $closedPr Closed")
            view.updatePullRequests(prs)
            return
        }
        view.showEmptyPullRequestMessage()
    }

    override fun getPullRequestError() {
        view.hideProgressBar()
        view.showNetworkError()
    }

}