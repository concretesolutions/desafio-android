package cl.jesualex.desafio_android.repo.data.local.entity

import io.realm.RealmObject

/**
 * Created by jesualex on 2019-05-28.
 */

open class LicenseLocal(
    var key : String = "",
    var name : String = "",
    var spdx_id : String = "",
    var url : String? = "",
    var node_id : String = ""
): RealmObject(){
    fun cascadeDelete() {
        deleteFromRealm()
    }
}