package cl.carteaga.querygithub.models

import com.google.gson.annotations.SerializedName

data class PullRequest(

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("user")
	val user: User? = null
)