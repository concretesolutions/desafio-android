package cl.jesualex.desafio_android.fork

/**
 * Created by jesualex on 2019-05-28.
 */

data class ForkBase (
    val url : String,
    val id : Int,
    val node_id : String,
    val html_url : String,
    val diff_url : String,
    val patch_url : String,
    val issue_url : String,
    val number : Int,
    val state : String,
    val locked : Boolean,
    val title : String,
    val user : User,
    val body : String,
    val created_at : String,
    val updated_at : String,
    val closed_at : String,
    val merged_at : String,
    val merge_commit_sha : String,
    val assignee : String,
    val assignees : List<String>,
    val requested_reviewers : List<String>,
    val requested_teams : List<String>,
    val labels : List<String>,
    val milestone : String,
    val commits_url : String,
    val review_comments_url : String,
    val review_comment_url : String,
    val comments_url : String,
    val statuses_url : String,
    val head : Link,
    val base : Base,
    val _links : Links,
    val author_association : String
)