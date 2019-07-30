package wilquer.lima.desafioconcrete.ui.repository

import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wilquer.lima.desafioconcrete.data.response.RepositoryResponse
import wilquer.lima.desafioconcrete.network.RepositoryService
import wilquer.lima.desafioconcrete.network.RetrofitApi
import wilquer.lima.desafioconcrete.util.Constants

class RepositoryPresenter(val view: RepositoryContract.View) : RepositoryContract.Presenter{

    init {
        view.initView()
    }

    override fun getRepositories(pageNumber: Int) {
        view.setProgress(true)

        doAsync {
            val apiService = RetrofitApi(Constants.GENERAL_URL).client.create(RepositoryService::class.java)

            val call = apiService.getRepositories(pageNumber)
            call.enqueue(object : Callback<RepositoryResponse>{
                override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                    view.setProgress(false)
                    view.error(t.message.toString())
                }

                override fun onResponse(call: Call<RepositoryResponse>, response: Response<RepositoryResponse>) {
                    view.setProgress(false)
                    view.repositories(response.body()?.items)
                }

            })
        }
    }

}