package cl.jesualex.desafio_android.repo.data.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by jesualex on 2019-05-28.
 */

open class UserLocal (
	var login : String = "",
	@PrimaryKey var id : Int = 0,
	var node_id : String = "",
	var avatar_url : String = "",
	var gravatar_id : String = "",
	var url : String = "",
	var html_url : String = "",
	var followers_url : String = "",
	var following_url : String = "",
	var gists_url : String = "",
	var starred_url : String = "",
	var subscriptions_url : String = "",
	var organizations_url : String = "",
	var repos_url : String = "",
	var events_url : String = "",
	var received_events_url : String = "",
	var type : String = "",
	var site_admin : Boolean = false,
	var name : String? = ""
): RealmObject(){
	fun cascadeDelete() {
		deleteFromRealm()
	}
}