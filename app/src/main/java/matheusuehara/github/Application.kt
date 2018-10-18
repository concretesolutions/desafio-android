package matheusuehara.github

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import java.lang.Long.MAX_VALUE

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        val builder = Picasso.Builder(this).downloader(OkHttp3Downloader(this,MAX_VALUE))
        val built = builder.build()
        Picasso.setSingletonInstance(built)
    }
}