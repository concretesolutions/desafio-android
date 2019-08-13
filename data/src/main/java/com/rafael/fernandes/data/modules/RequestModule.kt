package com.rafael.fernandes.data.modules

import com.rafael.fernandes.data.network.RestApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class RequestModule {
    @Provides
    fun provideRequestApi(
        @Named("RetrofitApi") retrofit: Retrofit
    ): RestApi {
        return retrofit.create(RestApi::class.java)
    }
}