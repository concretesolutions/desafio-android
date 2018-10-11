package com.diegoferreiracaetano.github.di

import com.diegoferreiracaetano.data.pull.PullImpRepository
import com.diegoferreiracaetano.data.repo.RepoImpRepository
import com.diegoferreiracaetano.domain.pull.PullRepository
import com.diegoferreiracaetano.domain.pull.interactor.CallbackPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.GetListPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.SavePullInicialInteractor
import com.diegoferreiracaetano.domain.pull.interactor.SavePullPageInteractor
import com.diegoferreiracaetano.domain.repo.RepoRepository
import com.diegoferreiracaetano.domain.repo.interactor.CallbackRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.GetListRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.SaveRepoInicialInteractor
import com.diegoferreiracaetano.domain.repo.interactor.SaveRepoPageInteractor
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val repositoryModule : Module = module {

    single{ RepoImpRepository(get(),get()) as RepoRepository}
    single{ PullImpRepository(get(),get()) as PullRepository }

    single { GetListRepoInteractor(get()) }
    single { CallbackRepoInteractor(get(),get()) }
    single { SaveRepoInicialInteractor(get())}
    single { SaveRepoPageInteractor(get()) }

    single { GetListPullInteractor(get()) }
    single { CallbackPullInteractor(get(),get()) }
    single { SavePullInicialInteractor(get()) }
    single { SavePullPageInteractor(get()) }

}
