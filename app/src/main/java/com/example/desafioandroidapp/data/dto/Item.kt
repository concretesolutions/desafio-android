package com.example.desafioandroidapp.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val node_id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val full_name: String,
    @SerializedName("private")
    val private : Boolean,
    @SerializedName("owner")
    val owner : Owner,
    @SerializedName("stargazers_count")
    val stargazers_count: String,
    @SerializedName("forks_count")
    val forks_count: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("pulls_url")
    val pulls_url: String
) : Parcelable
