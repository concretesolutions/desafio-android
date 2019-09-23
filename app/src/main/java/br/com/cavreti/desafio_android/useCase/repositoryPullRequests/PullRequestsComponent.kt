package br.com.cavreti.desafio_android.useCase.repositoryPullRequests

import br.com.cavreti.desafio_android.applicationCore.CustomScope
import br.com.cavreti.desafio_android.applicationCore.ServiceComponent
import dagger.Component

@CustomScope
@Component(dependencies = [ServiceComponent::class], modules = [PullRequestsModule::class])
interface PullRequestsComponent {
    fun inject(activity: PullRequestsActivity)
}