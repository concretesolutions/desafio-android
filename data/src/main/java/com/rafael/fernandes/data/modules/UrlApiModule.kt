package com.rafael.fernandes.data.modules

import com.rafael.fernandes.data.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
open class UrlApiModule {
    @Provides
    @Named("UrlApi")
    open fun provideUrlApi(): String {
        val host = BuildConfig.GITHUB_URL
        return "$host/"
    }
}