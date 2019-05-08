package com.gdavidpb.github.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.gdavidpb.github.data.source.local.GitHubDatabase
import com.gdavidpb.github.data.source.remote.GitHubApi
import com.gdavidpb.github.presentation.viewModels.MainViewModel
import com.gdavidpb.github.presentation.viewModels.PullViewModel
import com.gdavidpb.github.utils.DATABASE_NAME
import com.gdavidpb.github.utils.URL_BASE_GITHUB_API
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    /* Android Services */

    single {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /* Retrofit */

    single {
        OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(URL_BASE_GITHUB_API)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /* Database */

    single {
        Room.databaseBuilder(
            androidContext(),
            GitHubDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single {
        get<Retrofit>()
            .create(GitHubApi::class.java) as GitHubApi
    }

    /* Picasso */

    single {
        Picasso.get()
    }

    /* View models */

    viewModel<MainViewModel>()
    viewModel<PullViewModel>()

    /* Use cases */

    /* Factories */

}