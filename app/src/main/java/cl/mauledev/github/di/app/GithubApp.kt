package cl.mauledev.github.di.app

import android.app.Application
import cl.mauledev.github.di.components.DaggerGeneralComponent
import cl.mauledev.github.di.components.GeneralComponent
import cl.mauledev.github.di.modules.GeneralModule
import timber.log.Timber

class GithubApp: Application() {

    companion object {

        lateinit var generalComponent: GeneralComponent

    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initDagger() {
        generalComponent = DaggerGeneralComponent.builder()
                .generalModule(GeneralModule(this))
                .build()

        generalComponent.inject(this)
    }
}