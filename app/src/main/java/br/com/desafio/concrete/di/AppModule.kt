@file:JvmName("AppModuleKt")

package br.com.desafio.concrete.di


import android.content.Context
import br.com.desafio.concrete.network.ApiDataSource
import br.com.desafio.concrete.network.ApiService
import br.com.desafio.concrete.view.pullrequest.PullRequestContract
import br.com.desafio.concrete.view.pullrequest.PullRequestsPresenter
import br.com.desafio.concrete.view.repository.RepositoryListContract
import br.com.desafio.concrete.view.repository.RepositoryListPresenter
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/**
 * Created by Malkes on 24/09/2018.
 */

class AppModule{

    companion object {
        fun getModule(context: Context): Module {

            return module {

                single<ApiDataSource> { ApiService(context) }

                factory { RepositoryListPresenter(get()) as RepositoryListContract.Presenter }

                factory { PullRequestsPresenter(get()) as PullRequestContract.Presenter}

            }
        }
    }

}



