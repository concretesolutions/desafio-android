package cl.jesualex.desafio_android.base.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jesualex on 16-03-19.
 */
object ApiServiceFactory {
    fun <T> build(client: OkHttpClient.Builder, serviceClass: Class<T>, urlBase: String): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }

    fun <T> build(serviceClass: Class<T>, urlBase: String): T {
        return build(OkHttpClient.Builder(), serviceClass, urlBase)
    }
}
