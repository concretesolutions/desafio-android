package com.hako.githubapi

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.hako.githubapi.features.repos.RepoListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(RepoListActivity::class.java)

    @Test
    fun doesItOpens() {
        Thread.sleep(3000)
    }
}
