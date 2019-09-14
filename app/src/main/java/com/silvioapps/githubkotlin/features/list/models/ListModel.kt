package com.silvioapps.githubkotlin.features.list.models

import java.io.Serializable
import javax.inject.Singleton

@Singleton
data class ListModel(
	var showLoading : Boolean = false,
	val stargazers_count: Int? = null,
	val id: Int? = null,
	val name: String? = null,
	val description: String? = null,
	val owner: Owner? = null,
	val forks_count: Int? = null
): Serializable
