package com.example.lucasdias.mvvmpoc.domain.entity

import com.google.gson.annotations.SerializedName



data class RepositoryList(@SerializedName("items") var repositories: List<Repository>)
