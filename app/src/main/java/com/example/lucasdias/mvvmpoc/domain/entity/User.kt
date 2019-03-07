package com.example.lucasdias.mvvmpoc.domain.entity

import com.google.gson.annotations.SerializedName

data class User(var login: String, @SerializedName("avatar_url")var avatarUrl: String )