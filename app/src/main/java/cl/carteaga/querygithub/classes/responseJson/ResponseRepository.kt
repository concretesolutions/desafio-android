package cl.carteaga.querygithub.classes.responseJson

import com.google.gson.annotations.SerializedName

data class ResponseRepository(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)