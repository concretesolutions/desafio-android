package com.silvioapps.githubkotlin.features.details.models

import java.io.Serializable

data class User(
	val login: String? = null,
	val avatar_url: String? = null
): Serializable
