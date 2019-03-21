package cl.carteaga.querygithub.classes.responseJson

import com.google.gson.annotations.SerializedName

data class Commits(

	@field:SerializedName("href")
	val href: String? = null
)