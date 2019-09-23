package br.com.cavreti.desafio_android.useCase.repositoryPullRequests

import br.com.cavreti.desafio_android.applicationCore.CustomScope
import dagger.Module
import dagger.Provides

@Module
class PullRequestsModule(private val view: PullRequestsContract.View) {

    @Provides
    @CustomScope
    internal fun providePullRequestContractView(): PullRequestsContract.View {
        return view
    }
}