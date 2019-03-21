package cl.carteaga.querygithub.classes.responseJson

import com.google.gson.annotations.SerializedName

data class Issue(

	@field:SerializedName("href")
	val href: String? = null
)