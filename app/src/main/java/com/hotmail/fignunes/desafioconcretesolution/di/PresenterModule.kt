package com.hotmail.fignunes.desafioconcretesolution.di

import com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.GitRepositoryContract
import com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.GitRepositoryPresenter
import com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.PullRequestContract
import com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.PullRequestPresenter
import com.hotmail.fignunes.desafioconcretesolution.presentation.splash.SplashContract
import com.hotmail.fignunes.desafioconcretesolution.presentation.splash.SplashPresenter
import org.koin.dsl.module

object PresenterModule {

    val presenterModule = module {
        factory { (contract: GitRepositoryContract) -> GitRepositoryPresenter(contract,get(), get(), get(), get()) }
        factory { (contract: SplashContract) -> SplashPresenter(contract) }
        factory { (contract: PullRequestContract) -> PullRequestPresenter(contract, get(), get(), get()) }
    }
}