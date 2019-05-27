package com.losingtimeapps.javahub.ui.home

import com.losingtimeapps.javahub.common.di.modules.ActivityModule
import com.losingtimeapps.javahub.common.di.scopes.ActivityScope
import com.losingtimeapps.javahub.ui.home.pullrequest.PullRequestComponent
import com.losingtimeapps.javahub.ui.home.repository.RepositoryComponent
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface HomeActivityComponent : RepositoryComponent.RepositoryComponentCreator ,
    PullRequestComponent.PullRequestComponentCreator{

    fun inject(homeActivity: HomeActivity)
}
