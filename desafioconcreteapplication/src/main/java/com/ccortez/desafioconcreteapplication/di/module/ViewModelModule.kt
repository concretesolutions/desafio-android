package com.ccortez.desafioconcreteapplication.di.module

import android.app.Application
import androidx.lifecycle.ViewModel
import com.ccortez.desafioconcreteapplication.MVVMApplication
import com.ccortez.desafioconcreteapplication.di.ViewModelKey
import com.ccortez.desafioconcreteapplication.viewmodel.RepositoryListViewModel
import com.ccortez.desafioconcreteapplication.viewmodel.RepositoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindApplication(app: MVVMApplication?): Application?

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryListViewModel::class)
    abstract fun bindCarListViewModel(carListViewModel: RepositoryListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryViewModel::class)
    abstract fun bindCarViewModel(carViewModel: RepositoryViewModel): ViewModel


}
