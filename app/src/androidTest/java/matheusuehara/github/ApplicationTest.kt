package matheusuehara.github

import android.app.Application
import android.os.AsyncTask
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import matheusuehara.github.BuildConfig.API_URL
import matheusuehara.github.di.networkModule
import matheusuehara.github.di.repositoryModule
import matheusuehara.github.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationTest : Application() {

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setInitComputationSchedulerHandler {
            Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        RxJavaPlugins.setInitIoSchedulerHandler {
            Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        startKoin {

            androidContext(this@ApplicationTest)

            modules(listOf(
                    viewModelModule,
                    repositoryModule,
                    networkModule
            ))

            properties(mapOf(API_URL to "http://localhost:8080/"))
        }
    }
}