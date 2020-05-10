package com.hotmail.fignunes.desafioconcretesolution.pullrequest

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.hotmail.fignunes.desafioconcretesolution.R
import com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.GitRepositoryActivity
import com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.PullRequestActivity
import com.hotmail.fignunes.skytestefabionunes.model.GitRepositoryItem
import com.hotmail.fignunes.skytestefabionunes.model.GitRepositoryOwner
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PullRequestTest {

    val gitRepositoryItem = GitRepositoryItem(
        121395510,
        "CS-Notes",
        "CyC2018/CS-Notes",
        GitRepositoryOwner(
            "CyC2018",
            36260787,
            "https://avatars3.githubusercontent.com/u/36260787?v=4",
            "https://api.github.com/users/CyC2018",
            "",
            ""
        ),
        ":books: 技术面试必备基础知识、Leetcode、计算机操作系统、计算机网络、系统设计、Java、Python、C++",
        100424,
        32674
    )

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(PullRequestActivity::class.java, false, false)

    @Before
    fun setup() {
        val intent = Intent()
        intent.putExtra(GitRepositoryActivity.GIT_REPOSITORY_ITEM, gitRepositoryItem)
        activityTestRule.launchActivity(intent)
    }

    @Test
    fun fieldsVisible() {
        Thread.sleep(2000)
        onView(withId(R.id.pullRequestOpened)).check(matches(isDisplayed()))
        onView(withId(R.id.pullRequestClosed)).check(matches(isDisplayed()))

        Thread.sleep(2000)
    }

}