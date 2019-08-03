package com.example.artul.models

data class Generic(var total_count: Int,
                   var incomplete_results: Boolean,
                   var items: ArrayList<Repository>)