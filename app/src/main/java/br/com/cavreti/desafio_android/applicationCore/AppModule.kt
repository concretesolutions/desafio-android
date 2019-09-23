package br.com.cavreti.desafio_android.applicationCore

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }
}