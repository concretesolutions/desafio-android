package com.losingtimeapps.javahub.ui.home.pullrequest

import com.losingtimeapps.javahub.common.di.scopes.FragmentScope
import com.losingtimeapps.javahub.ui.home.repository.RepositoryModule
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PullRequestModule::class])
interface PullRequestComponent {

    fun inject(fragment: PullRequestFragment)

    interface PullRequestComponentCreator {

        fun pullRequestComponent(): PullRequestComponent
    }
}
