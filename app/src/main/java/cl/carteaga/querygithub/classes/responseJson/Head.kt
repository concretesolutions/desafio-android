package cl.carteaga.querygithub.classes.responseJson


import com.google.gson.annotations.SerializedName


data class Head(

	@field:SerializedName("ref")
	val ref: String? = null,

	@field:SerializedName("repo")
	val repo: Repo? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("sha")
	val sha: String? = null,

	@field:SerializedName("user")
	val user: User? = null
)