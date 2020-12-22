package com.ccortez.desafioconcreteapplication.service.model

import com.google.gson.annotations.SerializedName

data class Milestone (
    @field:SerializedName("open_issues")
    var openIssues: Int? = null,
    @field:SerializedName("closed_issues")
    var closedIssues: Int? = null
)
