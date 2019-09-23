package com.anderson.apigithub_mvvm.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anderson.apigithub_mvvm.di.ViewModelKey
import com.anderson.apigithub_mvvm.di.provider.ViewModelProviderFactory
import com.anderson.apigithub_mvvm.feature.main.viewmodel.MainViewModel
import com.anderson.apigithub_mvvm.feature.pullRequest.viewmodel.PullRequestViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by anderson on 21/09/19.
 */
@Module
abstract class ViewModelBuilder {

    // Main
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    // Pull
    @Binds
    @IntoMap
    @ViewModelKey(PullRequestViewModel::class)
    abstract fun bindMPullRequestViewModel(pullRequestViewModel: PullRequestViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}