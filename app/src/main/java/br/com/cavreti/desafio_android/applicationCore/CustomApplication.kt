package br.com.cavreti.desafio_android.applicationCore

import androidx.multidex.MultiDexApplication
import br.com.cavreti.desafio_android.util.Constants

open class CustomApplication : MultiDexApplication() {

    private lateinit var serviceComponent: ServiceComponent

    override fun onCreate() {
        super.onCreate()
        startServiceModule()
    }

    open fun startServiceModule() {
        serviceComponent = DaggerServiceComponent.builder()
            .appModule(AppModule(this))
            .serviceModule(ServiceModule(Constants.BASE_URL, this))
            .build()
    }

    open fun getServiceComponent() : ServiceComponent {
        return serviceComponent
    }

}