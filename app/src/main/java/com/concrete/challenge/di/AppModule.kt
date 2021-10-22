package com.concrete.challenge.di

import com.concrete.challenge.BuildConfig
import com.concrete.challenge.domain.io.APIService
import com.concrete.challenge.presentation.viewmodel.PullRequestViewModel
import com.concrete.challenge.presentation.viewmodel.RepositoryViewModel
import com.concrete.challenge.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<APIService> { get<Retrofit>().create(APIService::class.java) }

    viewModel<RepositoryViewModel>()

    viewModel<UserViewModel>()

    viewModel<PullRequestViewModel>()

}