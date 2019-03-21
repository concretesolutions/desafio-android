package cl.carteaga.querygithub.models

import cl.carteaga.querygithub.utils.responseJson.ItemsItem
import com.google.gson.annotations.SerializedName

data class Repository(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)