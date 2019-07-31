package br.com.concrete.githubconcretechallenge.di

import br.com.concrete.githubconcretechallenge.features.repositories.view.RepositoriesAdapter
import br.com.concrete.githubconcretechallenge.features.repositories.viewmodel.RepositoriesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by georgemcjr on 2019-07-31.
 */

val applicationModule = module {
    factory { RepositoriesAdapter() }

    viewModel { RepositoriesListViewModel() }
}