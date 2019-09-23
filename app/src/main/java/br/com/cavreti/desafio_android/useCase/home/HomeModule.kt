package br.com.cavreti.desafio_android.useCase.home

import br.com.cavreti.desafio_android.applicationCore.CustomScope
import dagger.Module
import dagger.Provides

@Module
class HomeModule(private val view: HomeContract.View) {

    @Provides
    @CustomScope
    internal fun provideHomeContractView(): HomeContract.View {
        return view
    }

}