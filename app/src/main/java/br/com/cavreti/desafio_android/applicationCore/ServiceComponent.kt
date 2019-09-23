package br.com.cavreti.desafio_android.applicationCore

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ServiceModule::class])
interface ServiceComponent {

    fun retrofit() : Retrofit
}