package com.ccortez.desafioconcrete.di.module

import com.ccortez.desafioconcrete.data.local.dao.UserDao
import com.ccortez.desafioconcrete.data.remote.ApiService
import com.ccortez.desafioconcrete.factory.UserDataSourceFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
class UserDataSourceFactoryModule {

    @Provides
    fun provideUserDataSourceFactory(
        userDao: UserDao,
        scope: CoroutineScope,
        apiService: ApiService
    ) = UserDataSourceFactory(
        userDao,
        scope,
        apiService
    )
}