package br.com.cavreti.desafio_android.useCase.home

import br.com.cavreti.desafio_android.applicationCore.CustomScope
import br.com.cavreti.desafio_android.applicationCore.ServiceComponent
import dagger.Component

@CustomScope
@Component(dependencies = [ServiceComponent::class],modules = [HomeModule::class])
interface HomeComponent {
    fun inject(activity: HomeActivity)
}