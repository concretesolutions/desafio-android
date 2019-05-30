package cl.jesualex.desafio_android.repo.data.domain.entity

import java.util.*

/**
 * Created by jesualex on 2019-05-28.
 */

data class Pull (
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
    val user : User?,
    val body : String,
    val created_at : Date?,
    val updated_at : Date?,
    val closed_at : Date?,
    val merged_at : Date?,
    val merge_commit_sha : String,
    val assignee : User?,
    val assignees : List<User>,
    val requested_reviewers : List<User>,
    val requested_teams : List<User>,
    val labels : List<Label>,
    val commits_url : String,
    val review_comments_url : String,
    val review_comment_url : String,
    val comments_url : String,
    val statuses_url : String,
    val head : Base?,
    val base : Base?,
    val _links : Links,
    val author_association : String
)