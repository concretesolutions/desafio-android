package cl.carteaga.querygithub.classes.responseJson

import com.google.gson.annotations.SerializedName

data class Links(

	@field:SerializedName("comments")
	val comments: Comments? = null,

	@field:SerializedName("issue")
	val issue: Issue? = null,

	@field:SerializedName("self")
	val self: Self? = null,

	@field:SerializedName("review_comments")
	val reviewComments: ReviewComments? = null,

	@field:SerializedName("commits")
	val commits: Commits? = null,

	@field:SerializedName("statuses")
	val statuses: Statuses? = null,

	@field:SerializedName("html")
	val html: Html? = null,

	@field:SerializedName("review_comment")
	val reviewComment: ReviewComment? = null
)