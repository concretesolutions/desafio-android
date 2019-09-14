package com.silvioapps.githubkotlin.features.list.models

import java.io.Serializable
import javax.inject.Singleton

@Singleton
data class Owner(
	val login: String? = null,
	val avatar_url: String? = null,
	val id: Int? = null
): Serializable
