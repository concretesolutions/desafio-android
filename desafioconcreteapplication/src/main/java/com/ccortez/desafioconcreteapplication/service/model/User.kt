package com.ccortez.desafioconcreteapplication.service.model

data class User (
    var received_events_url: String? = null,
    var organizations_url: String? = null,
    var avatar_url: String? = null,
    var gravatar_id: String? = null,
    var gists_url: String? = null,
    var starred_url: String? = null,
    var site_admin: String? = null,
    var type: String? = null,
    var url: String? = null,
    var id: String? = null,
    var html_url: String? = null,
    var following_url: String? = null,
    var events_url: String? = null,
    var login: String? = null,
    var subscriptions_url: String? = null,
    var repos_url: String? = null,
    var followers_url: String? = null
)