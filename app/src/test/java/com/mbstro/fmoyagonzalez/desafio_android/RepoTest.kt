package com.mbstro.fmoyagonzalez.desafio_android


import org.junit.Test

import org.junit.Assert.*

class RepoTest {
    private val repo = Repo("zulkic", "this is a description", 21, 12,Owner("zulkic", "http://www.google.cl"))

    @Test
    fun getName() {
        assertEquals(repo.name, "zulkic")
    }

    @Test
    fun getDescription() {
        assertEquals(repo.description, "this is a description")
        assertNotEquals(repo.description, "This is a description")
    }

    @Test
    fun getForks_count() {
        assertNotEquals(repo.forks_count, "21")
        assertEquals(repo.forks_count, 21)
    }

    @Test
    fun getStargazers_count() {
        assertNotEquals(repo.stargazers_count, "21")
        assertEquals(repo.stargazers_count, 12)
    }

    @Test
    fun getOwnerLogin() {
        assertSame(repo.owner.login,"zulkic")
    }

    @Test
    fun getOwnerAvatarUrl() {
        assertSame(repo.owner.avatar_url,"http://www.google.cl")
    }
}