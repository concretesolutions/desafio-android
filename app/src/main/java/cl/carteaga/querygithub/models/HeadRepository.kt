package cl.carteaga.querygithub.models

import com.google.gson.annotations.SerializedName

data class HeadRepository(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: List<Repository?>? = null
)