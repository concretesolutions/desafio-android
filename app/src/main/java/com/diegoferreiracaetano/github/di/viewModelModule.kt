package com.diegoferreiracaetano.github.di

import com.diegoferreiracaetano.github.ui.pull.PullViewModel
import com.diegoferreiracaetano.github.ui.repo.RepoViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelModule : Module = module {
    viewModel { RepoViewModel(get(), get()) }
    viewModel { PullViewModel(get(), get()) }
}
