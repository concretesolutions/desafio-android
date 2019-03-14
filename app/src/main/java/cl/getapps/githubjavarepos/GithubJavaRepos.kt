package cl.getapps.githubjavarepos

import android.app.Application
import cl.getapps.githubjavarepos.core.di.applicationModule
import cl.getapps.githubjavarepos.core.di.pullRequestsModule
import cl.getapps.githubjavarepos.core.di.reposModule
import org.koin.android.ext.android.startKoin


class GithubJavaRepos: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            applicationModule,
            reposModule
            //pullRequestsModule
        ))
    }
}
