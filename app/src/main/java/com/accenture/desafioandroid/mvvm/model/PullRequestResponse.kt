package com.accenture.desafioandroid.mvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PullRequestResponse {
    var items: MutableList<PullRequest?>? = null
}