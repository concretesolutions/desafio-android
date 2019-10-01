package br.com.guilherme.solution.component

import br.com.guilherme.solution.module.ActivityModule
import br.com.guilherme.solution.ui.pullRequests.PullRequestsActivity
import br.com.guilherme.solution.ui.repositoryList.MainActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(pullRequestsActivity: PullRequestsActivity)
}