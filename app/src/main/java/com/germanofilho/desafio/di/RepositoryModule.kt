package com.germanofilho.desafio.di

import com.germanofilho.home.repository.HomeRepository
import com.germanofilho.home.repository.HomeRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<HomeRepository> { HomeRepositoryImpl() }
}