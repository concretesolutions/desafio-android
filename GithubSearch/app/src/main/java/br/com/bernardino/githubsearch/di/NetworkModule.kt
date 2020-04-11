package br.com.bernardino.githubsearch.di

import br.com.bernardino.githubsearch.network.ApiFactory
import br.com.bernardino.githubsearch.network.GithubApi
import org.koin.dsl.module

val networkModule = module {

    single {
        ApiFactory.create(
            webServicesApi = GithubApi::class.java,
            apiURL = "https://api.github.com/",
            debuggable = true
        )
    }
}