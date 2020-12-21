package com.ccortez.desafioconcrete.di.module

import androidx.lifecycle.ViewModel
import com.ccortez.desafioconcrete.ui.main.MainViewModel
import com.ccortez.desafioconcrete.ui.main.RepositoryViewModel
import com.ccortez.desafioconcrete.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryViewModel::class)
    abstract fun bindRepositoryViewModel(repositoryViewModel: RepositoryViewModel): ViewModel

}