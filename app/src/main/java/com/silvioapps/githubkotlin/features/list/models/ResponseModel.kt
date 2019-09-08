package com.silvioapps.githubkotlin.features.list.models

import java.io.Serializable

class ResponseModel : Serializable{
	val total_count: Int? = null
	val incomplete_results: Boolean? = null
	val items: MutableList<ListModel>? = null
}