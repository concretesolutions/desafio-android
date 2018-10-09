package com.mbstro.fmoyagonzalez.desafio_android

import org.junit.Test

import org.junit.Assert.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class PullRequestTest {
    var dDate = "2018-09-29T09:26:02Z"
    var df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    var cDate = df.parse(dDate)
    private val pullRequest = PullRequest("desafio-android", "this is a description", cDate, "http://www.google.cl",User("zulkic", "http://www.google.cl"))

    @Test
    fun getTitle() {
        assertEquals(pullRequest.title, "desafio-android")
    }

    @Test
    fun getBody() {
        assertEquals(pullRequest.body, "this is a description")
        assertNotEquals(pullRequest.body, "")
    }

    @Test
    fun getUpdated_at() {
        assertEquals(pullRequest.updated_at, df.parse(dDate))
    }

    @Test
    fun getHtml_url() {
        assertSame(pullRequest.html_url,"http://www.google.cl")
    }

    @Test
    fun getUserLogin() {
        assertSame(pullRequest.user.login,"zulkic")
    }

    @Test
    fun getUserAvatarUrl() {
        assertSame(pullRequest.user.avatar_url,"http://www.google.cl")
    }
}