package com.hotmail.fignunes.desafioconcretesolution.di

import com.hotmail.fignunes.desafioconcretesolution.presentation.common.CheckIsOnline
import com.hotmail.fignunes.desafioconcretesolution.presentation.common.StringHelper
import com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.actions.GetGitRepositories
import com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.actions.ResponsesToGitRepository
import com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.actions.GetPullRequest
import com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.actions.ResponsesToPullRequest
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object ActionsModule {

    val actionsModule = module {

        factory { GetGitRepositories(get()) }
        factory { CheckIsOnline(androidContext()) }
        factory { ResponsesToGitRepository() }
        factory { GetPullRequest(get()) }
        factory { ResponsesToPullRequest() }
        factory { StringHelper(androidContext()) }
    }
}