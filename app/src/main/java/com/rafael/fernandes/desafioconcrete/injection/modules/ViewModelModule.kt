package com.rafael.fernandes.desafioconcrete.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rafael.fernandes.desafioconcrete.injection.ViewModelKey
import com.rafael.fernandes.desafioconcrete.ui.activities.main.RepositoriesViewModel
import com.rafael.fernandes.desafioconcrete.ui.activities.pullrequest.PullRequestViewModel
import com.rafael.fernandes.desafioconcrete.ui.activities.splash.SplashViewModel
import com.rafael.fernandes.desafioconcrete.ui.common.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: RepositoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PullRequestViewModel::class)
    internal abstract fun bindPullRequestViewModel(viewModel: PullRequestViewModel): ViewModel


}