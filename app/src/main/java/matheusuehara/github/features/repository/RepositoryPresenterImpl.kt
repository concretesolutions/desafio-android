package matheusuehara.github.features.repository

import android.util.Log
import matheusuehara.github.api.GitHubService
import matheusuehara.github.contract.GitHubContract
import matheusuehara.github.contract.RepositoryContract
import matheusuehara.github.data.model.Repository
import matheusuehara.github.data.model.RepositoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class RepositoryPresenterImpl constructor(var view: RepositoryContract.View) : RepositoryContract.Presenter{

    private var service: GitHubContract.Service = GitHubService()
    private var currentPage:Int = 0
    companion object {
        private val language = "language:Java"
        private val sort = "stars"
    }

    override fun getRepositories() {
        view.showProgressBar()
        service.getAPI().getRepositories(language, sort,currentPage).enqueue(object : Callback<RepositoryResponse> {
            override fun onResponse(call: Call<RepositoryResponse>, response: Response<RepositoryResponse>) {
                if (response.code() == 200) {
                    currentPage++
                    val repositories = response.body()?.items as ArrayList<Repository>
                    getRepositorySuccess(repositories)
                } else {
                    getRepositoryError()
                }
            }
            override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                handleError(t)
            }
        })
    }

    private fun handleError(e:Throwable){
        Log.e("Error", e.message)
        view.hideProgressBar()
        view.showNetworkError()
    }

    override fun getRepositorySuccess(repositoryResult: List<Repository>?) {
        view.hideProgressBar()
        if (repositoryResult != null) {
            view.updateRepositories(repositoryResult)
        }else{
            view.showEmptyRepositoryMessage()
        }
    }

    override fun getRepositoryError() {
        view.hideProgressBar()
        view.showNetworkError()
    }

}