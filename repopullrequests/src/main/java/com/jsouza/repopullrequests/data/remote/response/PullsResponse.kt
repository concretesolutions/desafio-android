package com.jsouza.repopullrequests.data.remote.response

import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PullsResponse(
    var id: Int,
    var title: String?,
    var body: String?,
    @Json(name = "user") var owner: Owner?,
    @Json(name = "created_at") var createdAt: String?,
    @Json(name = "html_url") var url: String = "",
    var state: String?,
    var repositoryId: Long?
) {

    fun getCreatedAtDateString(): String {
        val isEmpty = createdAt?.isEmpty() ?: true
        return if (!isEmpty) {
            val format = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.getDefault()
            )
            val date: Date? = createdAt?.let {
                format.parse(it)
            }
            val brazilFormat = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            )
            brazilFormat.format(date?.time).toString()
        } else {
            ""
        }
    }
}
