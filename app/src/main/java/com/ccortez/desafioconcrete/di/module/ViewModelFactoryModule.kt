package com.ccortez.desafioconcrete.di.module

import androidx.lifecycle.ViewModelProvider
import com.ccortez.desafioconcrete.factory.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}