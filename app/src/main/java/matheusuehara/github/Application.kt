package matheusuehara.github

import android.app.Application
import matheusuehara.github.di.networkModule
import matheusuehara.github.di.repositoryModule
import matheusuehara.github.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(listOf(
                    viewModelModule,
                    networkModule,
                    repositoryModule
            ))
        }
    }
}