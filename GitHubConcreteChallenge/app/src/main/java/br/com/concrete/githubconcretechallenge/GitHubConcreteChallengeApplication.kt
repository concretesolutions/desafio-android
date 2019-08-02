package br.com.concrete.githubconcretechallenge

import android.app.Application
import br.com.concrete.githubconcretechallenge.di.applicationModule
import br.com.concrete.githubconcretechallenge.di.cacheModule
import br.com.concrete.githubconcretechallenge.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GitHubConcreteChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GitHubConcreteChallengeApplication)
            modules(listOf(applicationModule, retrofitModule, cacheModule))
        }
    }

}