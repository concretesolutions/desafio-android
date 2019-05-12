package com.gdavidpb.github.data.model.api

data class PullEntry(
    val id: Long,
    val title: String,
    val body: String,
    val number: Int,
    val html_url: String,
    val user: UserEntry,
    val created_at: String,
    val updated_at: String,
    val closed_at: String?,
    val merged_at: String?
)