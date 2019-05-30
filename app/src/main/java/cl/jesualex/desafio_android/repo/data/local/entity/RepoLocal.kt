package cl.jesualex.desafio_android.repo.data.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by jesualex on 2019-05-28.
 */

open class RepoLocal (
	@PrimaryKey var id : Int = 0,
	var node_id : String = "",
	var name : String = "",
	var full_name : String = "",
	var private : Boolean = false,
	var owner : UserLocal? = null,
	var html_url : String = "",
	var description : String = "",
	var fork : Boolean = false,
	var url : String = "",
	var forks_url : String = "",
	var keys_urls_url : String? = null,
	var collaborators_url : String = "",
	var teams_url : String = "",
	var hooks_url : String = "",
	var issue_events_url : String = "",
	var events_url : String = "",
	var assignees_url : String = "",
	var branches_url : String = "",
	var tags_url : String = "",
	var blobs_url : String = "",
	var git_tags_url : String = "",
	var git_refs_url : String = "",
	var trees_url : String = "",
	var statuses_url : String = "",
	var languages_url : String = "",
	var stargazers_url : String = "",
	var contributors_url : String = "",
	var subscribers_url : String = "",
	var subscription_url : String = "",
	var commits_url : String = "",
	var git_commits_url : String = "",
	var comments_url : String = "",
	var issue_comment_url : String = "",
	var contents_url : String = "",
	var compare_url : String = "",
	var merges_url : String = "",
	var archive_url : String = "",
	var downloads_url : String = "",
	var issues_url : String = "",
	var pulls_url : String = "",
	var milestones_url : String = "",
	var notifications_url : String = "",
	var labels_url : String = "",
	var releases_url : String = "",
	var deployments_url : String = "",
	var created_at : Date? = null,
	var updated_at : Date? = null,
	var pushed_at : Date? = null,
	var git_url : String = "",
	var ssh_url : String = "",
	var clone_url : String = "",
	var svn_url : String = "",
	var homepage : String? = "",
	var size : Int = 0,
	var stargazers_count : Int = 0,
	var watchers_count : Int = 0,
	var language : String? = "",
	var has_issues : Boolean = false,
	var has_projects : Boolean = false,
	var has_downloads : Boolean = false,
	var has_wiki : Boolean = false,
	var has_pages : Boolean = false,
	var forks_count : Int = 0,
	var mirror_url : String? = "",
	var archived : Boolean = false,
	var disabled : Boolean = false,
	var open_issues_count : Int = 0,
	var license : LicenseLocal? = null,
	var forks : Int = 0,
	var open_issues : Int = 0,
	var watchers : Int = 0,
	var default_branch : String = "",
	var score : Double = 0.0
): RealmObject(){
	fun cascadeDelete() {
		license?.cascadeDelete()
		deleteFromRealm()
	}
}