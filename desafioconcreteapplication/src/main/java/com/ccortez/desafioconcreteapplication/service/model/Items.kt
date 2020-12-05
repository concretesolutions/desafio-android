package com.ccortez.desafioconcreteapplication.service.model

import android.os.Parcel
import android.os.Parcelable

data class Items (
    var id: Int,
    var name: String?,
    var description: String?,
    var owner: Owner? = null,
    var forks_count: String?,
    var stargazers_count: String?,
    var full_name: String?)