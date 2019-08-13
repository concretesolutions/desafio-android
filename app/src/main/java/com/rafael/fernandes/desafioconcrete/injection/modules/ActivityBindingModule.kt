package com.rafael.fernandes.desafioconcrete.injection.modules

import com.rafael.fernandes.desafioconcrete.ui.activities.main.GitRepositoriesActivity
import com.rafael.fernandes.desafioconcrete.ui.activities.main.MainActivity
import com.rafael.fernandes.desafioconcrete.ui.activities.pullrequest.PullRequestActivity
import com.rafael.fernandes.desafioconcrete.ui.activities.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun bindPullRequestActivity(): PullRequestActivity

    @ContributesAndroidInjector
    internal abstract fun bindGitRepositoriesActivity(): GitRepositoriesActivity

}