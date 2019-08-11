package com.example.desafioconcentresolutions

import com.example.desafioconcentresolutions.viewmodels.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { MainViewModel(androidApplication()) }
}

val repositoriesModule: Module = module{
    single {  }
}

val dataSourcesModule:Module = module {

}