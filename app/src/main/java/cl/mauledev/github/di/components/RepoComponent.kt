package cl.mauledev.github.di.components

import cl.mauledev.github.di.modules.RepoModule
import cl.mauledev.github.di.scope.ActivityScope
import cl.mauledev.github.view.viewmodels.MainViewModel
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [(RepoModule::class)])
interface RepoComponent {

    fun inject(viewModel: MainViewModel)

}