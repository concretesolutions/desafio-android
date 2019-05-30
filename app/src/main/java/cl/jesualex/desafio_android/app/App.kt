package cl.jesualex.desafio_android.app

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by jesualex on 2019-05-28.
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())
    }
}