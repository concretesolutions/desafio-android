package com.concrete.desafio.di

import com.concrete.desafio.data.GestorDeRepositorios
import com.concrete.desafio.data.GitHubServiceAPI
import com.concrete.desafio.viewmodels.RepositorioViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object ModulosDeDependencias {

    val moduloDaApp = module {

        single { GitHubServiceAPI() }

        factory { GestorDeRepositorios( get()) }

        viewModel { RepositorioViewModel( get()) }
    }
}