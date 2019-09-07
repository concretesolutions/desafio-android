package com.silvioapps.githubkotlin.features.list.models

import java.io.Serializable

class ResponseModel : Serializable{
	val totalCount: Int? = null
	val incompleteResults: Boolean? = null
	val items: MutableList<ListModel>? = null
}