package com.silvioapps.githubkotlin.features.list.models

import java.io.Serializable
import javax.inject.Singleton

@Singleton
data class ResponseModel(
	val items: MutableList<ListModel>? = null
): Serializable