package com.anderson.apigithub_mvvm.data.presentation

import java.io.Serializable

class RepositoryPresentation (
    val name: String,
    val login: String,
    val starsCount: String,
    val forksCount: String,
    val pullsUrls: String,
    val avatarUrl: String
): Serializable