package com.losingtimeapps.javahub.ui.home.repository

import com.losingtimeapps.javahub.common.di.scopes.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [RepositoryModule::class])
interface RepositoryComponent {

    fun inject(fragment: RepositoryFragment)

    interface RepositoryComponentCreator {

        fun repositoryComponent(): RepositoryComponent
    }
}
