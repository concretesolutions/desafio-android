package cl.mauledev.github.di.modules

import android.content.Context
import cl.mauledev.github.data.datasources.remote.api.AppService
import cl.mauledev.github.data.datasources.remote.api.RepoAPI
import cl.mauledev.github.di.app.GithubApp
import cl.mauledev.github.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.internal.bind.util.ISO8601Utils
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.text.ParsePosition
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class GeneralModule(var githubApp: GithubApp) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context {
        return githubApp
    }

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient {

        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Timber.d("OkHTTP: %s", message)
        }).apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
                .connectTimeout(Constants.REMOTE_CONNECT_TIMEOUT, TimeUnit.MINUTES)
                .readTimeout(Constants.REMOTE_READ_TIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(Constants.REMOTE_WRITE_TIMEOUT, TimeUnit.MINUTES)
                .addInterceptor(logger)
                .build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {

        val deserializer = JsonDeserializer<Date> { json, _, _ ->
            return@JsonDeserializer ISO8601Utils.parse(json.asString,
                    ParsePosition(0))
        }

        val gsonBuilder = GsonBuilder().apply {
            this.setDateFormat(Constants.DATE_FORMAT)
            this.registerTypeAdapter(Date::class.java, deserializer)
        }

        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesRESTService(retrofit: Retrofit): AppService {
        return retrofit.create<AppService>(AppService::class.java)
    }

    @Provides
    @Singleton
    fun providesApi(appService: AppService): RepoAPI {
        return RepoAPI(appService)
    }
}