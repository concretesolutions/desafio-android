package dev.theuzfaleiro.trendingongithub.ui.feature.home.model.response

import com.google.gson.annotations.SerializedName

data class Repositories(@SerializedName(value = "items") val repositories: List<Repository>)