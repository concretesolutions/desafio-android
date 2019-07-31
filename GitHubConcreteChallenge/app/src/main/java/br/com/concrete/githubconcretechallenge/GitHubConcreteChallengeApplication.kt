package br.com.concrete.githubconcretechallenge

import android.app.Application
import br.com.concrete.githubconcretechallenge.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by georgemcjr on 2019-07-31.
 */
class GitHubConcreteChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GitHubConcreteChallengeApplication)
            modules(applicationModule)
        }
    }

}