package com.example.lucasdias.mvvmpoc

import android.app.Application
import com.example.lucasdias.mvvmpoc.di.*
import org.koin.android.ext.android.startKoin


class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(repositoryViewModule, repositoryViewModelModule,
                repositoryRepositoryModule, repositoryServiceModule, repositoryUseCaseModule,
                serviceModule, pullRequestViewModule, pullRequestViewModelModule,
                pullRequestRepositoryModule, pullRequestServiceModule, pullRequestUseCaseModule,
                dataBaseModule))
    }
}