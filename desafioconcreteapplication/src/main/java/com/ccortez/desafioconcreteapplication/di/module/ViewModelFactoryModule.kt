package com.ccortez.desafioconcreteapplication.di.module

import androidx.lifecycle.ViewModelProvider
import com.ccortez.desafioconcreteapplication.viewmodel.MyViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * ViewModelFactoryModule responsible for providing [ViewModelProviderFactory]
 *
 * Annotated with Module to tell dagger it is a module to provide [ViewModelProviderFactory]
 *
 * Annotated with bind annotation to efficiently provide dependencies similar to provides annotation
 */
@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: MyViewModelFactory) : ViewModelProvider.Factory
}