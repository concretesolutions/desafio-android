package com.example.eloyvitorio.githubjavapop.data.model

import com.google.gson.annotations.SerializedName

class ResponseResult(
        @SerializedName("items")
        val repositories: List<Repository>
)
