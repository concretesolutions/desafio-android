package cl.jesualex.desafio_android.repo.data.local

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by jesualex on 2019-05-28.
 */

open class PullLocal (
    var url : String = "",
    @PrimaryKey var id : Int = 0,
    var node_id : String = "",
    var html_url : String = "",
    var diff_url : String = "",
    var patch_url : String = "",
    var issue_url : String = "",
    var number : Int = 0,
    var state : String = "",
    var locked : Boolean = false,
    var title : String = "",
    var user : UserLocal? = null,
    var body : String = "",
    var created_at : Date? = null,
    var updated_at : Date? = null,
    var closed_at : Date? = null,
    var merged_at : Date? = null,
    var merge_commit_sha : String = "",
    var assignee : String = "",
    var assignees : RealmList<String> = RealmList(),
    var requested_reviewers : RealmList<String> = RealmList(),
    var requested_teams : RealmList<String> = RealmList(),
    var labels : RealmList<String> = RealmList(),
    var milestone : String = "",
    var commits_url : String = "",
    var review_comments_url : String = "",
    var review_comment_url : String = "",
    var comments_url : String = "",
    var statuses_url : String = "",
    var head : BaseLocal? = null,
    var base : BaseLocal? = null,
    var self : String = "",
    var html : String = "",
    var issue : String = "",
    var comments : String = "",
    var review_comments : String = "",
    var review_comment : String = "",
    var commits : String = "",
    var statuses : String = "",
    var author_association : String = ""
): RealmObject(){
    fun cascadeDelete() {
        base?.cascadeDelete()
        head?.cascadeDelete()
        deleteFromRealm()
    }
}