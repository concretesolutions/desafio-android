package com.rafael.fernandes.domain.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class GitPullRequests(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("created_at")
    val createdAt: Date? = null,
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("user")
    val user: User? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    @SerializedName("updated_at")
    val updatedAt: Date? = null
) : Serializable {
    @SuppressLint("SimpleDateFormat")
    fun formatDate(): String {
        val dateFormat = "dd/MM/yyyy HH:mm:ss"
        try {
            return SimpleDateFormat(dateFormat).format(createdAt)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }
}
