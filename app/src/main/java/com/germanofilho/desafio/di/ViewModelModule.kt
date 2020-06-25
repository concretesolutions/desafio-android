package com.germanofilho.desafio.di

import com.germanofilho.home.presentation.viewmodel.HomeViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModelImpl(get()) }
}