package br.com.cavreti.desafio_android.applicationCore.base


import br.com.cavreti.desafio_android.applicationCore.CustomScope
import br.com.cavreti.desafio_android.applicationCore.ServiceComponent
import dagger.Component

@CustomScope
@Component(dependencies = [ServiceComponent::class])
interface BaseComponent {

    fun inject(activity: BaseActivity)
}