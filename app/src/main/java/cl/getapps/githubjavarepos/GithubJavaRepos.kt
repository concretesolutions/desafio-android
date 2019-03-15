package cl.getapps.githubjavarepos

import android.app.Application
import cl.getapps.githubjavarepos.core.di.threadModule
import cl.getapps.githubjavarepos.features.repopullrequests.di.pullRequestsModule
import cl.getapps.githubjavarepos.features.repos.di.reposModule
import org.koin.android.ext.android.startKoin

class GithubJavaRepos : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(
                threadModule,
                reposModule,
                pullRequestsModule
            )
        )
    }
}
