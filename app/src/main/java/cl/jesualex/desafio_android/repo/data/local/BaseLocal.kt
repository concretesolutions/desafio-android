package cl.jesualex.desafio_android.repo.data.local

import io.realm.RealmObject

/**
 * Created by jesualex on 2019-05-28.
 */

open class BaseLocal (
    var label : String = "",
    var ref : String = "",
    var sha : String = "",
    var user : UserLocal? = null,
    var repo : RepoLocal? = null
): RealmObject(){
    fun cascadeDelete() {
        deleteFromRealm()
    }
}