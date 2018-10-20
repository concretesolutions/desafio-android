package cl.mauledev.github.di.components

import android.app.Application
import cl.mauledev.github.di.modules.GeneralModule
import cl.mauledev.github.di.modules.RepoModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(GeneralModule::class)])
interface GeneralComponent {

    fun inject(application: Application)

    fun plus(module: RepoModule): RepoComponent
}