package br.com.cavreti.desafio_android.useCase.home

import android.util.Log
import br.com.cavreti.desafio_android.applicationCore.base.BasePresenter
import br.com.cavreti.desafio_android.network.RequestCallback
import retrofit2.Retrofit
import javax.inject.Inject
import br.com.cavreti.desafio_android.network.createRequest
import br.com.cavreti.desafio_android.network.response.RepositoryResponse
import br.com.cavreti.desafio_android.network.service.RepositoryService

class HomePresenter @Inject constructor(private val retrofit: Retrofit, private val view : HomeContract.View)
    : BasePresenter(), HomeContract.Presenter {

    override fun getRepositories(page: Int) {

        createRequest(retrofit.create(RepositoryService::class.java).getRepositories(page), object : RequestCallback {
            override fun onError(throwable: Throwable) {
                view.loadRepositoriesFailed()
            }

            override fun onSuccess(response: Any?) {
                val repositories = response as RepositoryResponse
                view.loadRepositories(repositories.items)
            }
        })
    }
}