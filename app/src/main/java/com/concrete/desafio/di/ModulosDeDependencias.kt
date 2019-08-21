package com.concrete.desafio.di

import com.concrete.desafio.data.GestorDePullRequest
import com.concrete.desafio.data.GestorDeRepositorios
import com.concrete.desafio.data.GitHubServiceAPI
import com.concrete.desafio.viewmodels.PullRequestViewModel
import com.concrete.desafio.viewmodels.RepositorioViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object ModulosDeDependencias {

    val moduloDeRepositorios = module {

        single { GitHubServiceAPI() }

        factory { GestorDeRepositorios( get()) }

        viewModel { RepositorioViewModel( get()) }
    }

    val moduloDePullRequest = module {

        factory { GestorDePullRequest( get()) }

        viewModel { PullRequestViewModel( get()) }
    }
}