package com.silvioapps.githubkotlin.features.list.models

import java.io.Serializable

class Response : Serializable{
	val totalCount: Int? = null
	val incompleteResults: Boolean? = null
	val items: List<ListModel?>? = null
}