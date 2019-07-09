package app.kelvao.javapop.domain.network

import app.kelvao.javapop.BuildConfig
import app.kelvao.javapop.domain.network.service.PullRequestsRestService
import app.kelvao.javapop.domain.network.service.RepositoriesRestService
import app.kelvao.javapop.domain.network.service.UserRestService
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {

    val retrofit: Retrofit = getRetrofitBuilder(getOkHttpClient()).build()

    val repositoriesService: RepositoriesRestService by lazy { retrofit.create(RepositoriesRestService::class.java) }
    val pullRequestsService: PullRequestsRestService by lazy { retrofit.create(PullRequestsRestService::class.java) }
    val userService: UserRestService by lazy { retrofit.create(UserRestService::class.java) }

    private fun getRetrofitBuilder(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))

    private fun getOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().apply {
        retryOnConnectionFailure(true)
        followRedirects(true)
        readTimeout(60, TimeUnit.SECONDS)
        addInterceptor { chain ->
            val builder = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
            chain.proceed(builder.build())
        }
    }.build()

}