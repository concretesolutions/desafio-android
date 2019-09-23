package com.anderson.apigithub_mvvm.di.builder

import com.anderson.apigithub_mvvm.feature.main.activity.MainActivity
import com.anderson.apigithub_mvvm.di.Activity
import com.anderson.apigithub_mvvm.feature.pullRequest.activity.PullRequestActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by anderson on 21/09/19.
 */
@Module
abstract class ActivityBuilder {

    // Main
    @Activity
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    // PullResquet
    @Activity
    @ContributesAndroidInjector
    abstract fun bindPullRequestActivity(): PullRequestActivity

}