package com.example.challengecskotlin

data class Data<Repo> (
    val total_count:String,
    val incomplete_results: Boolean,
    val items: Repo? = null
)