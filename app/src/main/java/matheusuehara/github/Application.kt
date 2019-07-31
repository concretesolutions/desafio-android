package matheusuehara.github

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import matheusuehara.github.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.lang.Long.MAX_VALUE

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        Picasso.setSingletonInstance(Picasso.Builder(this).downloader(OkHttp3Downloader(this,MAX_VALUE)).build())

        startKoin{
            androidContext(this@Application)
            modules(appModule)
        }
    }
}