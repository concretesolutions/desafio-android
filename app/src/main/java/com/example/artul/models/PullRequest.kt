package com.example.artul.models

import java.util.*

data class PullRequest (var id: Int,
                        var title: String,
                        var body: String,
                        var user: Author,
                        var html_url: String,
                        var created_at: Date)