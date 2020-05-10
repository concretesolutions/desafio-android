package com.hotmail.fignunes.desafioconcretesolution.repository.di

import com.hotmail.fignunes.desafioconcretesolution.repository.Repository
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.Api
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.services.GitRepositoryServices
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.services.PullRequestServices
import com.hotmail.fignunes.skytestefabionunes.repository.remote.RemoteRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object RepositoryModule {

    val repositoryModule = module {
        single { RemoteRepository() }
        single { Repository(androidApplication()) }

        single { Api<GitRepositoryServices>().create(GitRepositoryServices::class.java) }
        single { Api<PullRequestServices>().create(PullRequestServices::class.java) }
    }
}