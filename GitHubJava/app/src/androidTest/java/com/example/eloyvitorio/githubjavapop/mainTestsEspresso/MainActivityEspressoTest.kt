package com.example.eloyvitorio.githubjavapop.mainTestsEspresso

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.eloyvitorio.githubjavapop.R
import com.example.eloyvitorio.githubjavapop.data.network.CreateRetrofitImpl
import com.example.eloyvitorio.githubjavapop.ui.pullrequest.PullRequestsListActivity
import com.example.eloyvitorio.githubjavapop.ui.repositories.MainActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.BufferedReader
import java.io.InputStream

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {
    private val server = MockWebServer()

    private val testContext: Context = InstrumentationRegistry.getInstrumentation().getContext()

    @get:Rule
    val rule = IntentsTestRule(MainActivity::class.java,
            true, false)

    @Before
    fun setUp() {
        server.start()
        val rootUrl = server.url("/").toString()
        CreateRetrofitImpl.createApi(rootUrl)
    }

    @Test
    fun checkOnMain_fieldsAreDisplayed() {
        createMockOfRepositoriesList(true)
        rule.launchActivity(null)

        mainRepositoryItem {
            scrollToPositionInList(R.id.recyclerViewMainRepository, 0)
            repositoryTitleIsDisplayed("CS-Notes")
            repositoryDescripitonIsDisplayed(":books: Tech Interview Guide 技术面试必备基础知识、Leetcode 题解、Java、C++、Python、后端面试、操作系统、计算机网络、系统设计")
            repositoryForkNumberIsDisplayed("25920")
            repositoryStargazersIsDisplayed("81110")
            repositoryUserLoginIsDisplayed("CyC2018")
            repositoryUserNameIsDisplayed("CyC2018")
        }
    }

    @Test
    fun checkOnMain_scrollInList() {
        createMockOfRepositoriesList(true)
        rule.launchActivity(null)

        mainRepositoryItem {
            scrollToPositionInList(R.id.recyclerViewMainRepository, 7)
            repositoryTitleIsDisplayed("tinker")
        }
    }

    @Test
    fun checkOnMain_imagesAreDisplayed() {
        createMockOfRepositoriesList(true)
        rule.launchActivity(null)

        mainRepositoryItem {
            forkIconIsDisplayed(R.id.recyclerViewMainRepository,
                    0,
                    R.id.imageViewMainForkIcon)
            starIconIsDisplayed(
                    R.id.recyclerViewMainRepository,
                    0,
                    R.id.imageViewMainStarIcon
            )
            userPhotoIsDisplayed(
                    R.id.recyclerViewMainRepository,
                    0,
                    R.id.imageViewMainUserPhoto
            )
        }
    }

    @Test
    fun checkOnMain_pullRequestsListCalled() {
        createMockOfRepositoriesList(true)
        rule.launchActivity(null)

        mainRepositoryItem {
            clickOnPositionInList(R.id.recyclerViewMainRepository, 0)
            intendedPullRequestList(
                    PullRequestsListActivity::class.java.name,
                    "repositoryName",
                    "CS-Notes",
                    "login",
                    "CyC2018")
        }
    }

    @Test
    fun checkOnMain_errorFlowIsCalled() {
        val messageError = "Um erro ocorreu. \nTente carregar novamente."
        createMockOfRepositoriesList(false)
        rule.launchActivity(null)

        mainRepositoryItem {
            loadErrorImageIsDisplayed(R.id.imageViewErrorLoad)
            loadErrorMessageIsDisplayed(messageError)
            loadErrorRetryButtonIsClickable(R.id.buttonErrorLoadRetry)
        }
    }

    private fun createMockOfRepositoriesList(create: Boolean) {
        if (create) {
            val inputStream: InputStream = testContext.assets.open("repositories_response_p1.json")
            val content = inputStream.bufferedReader().use(BufferedReader::readText)
            server.enqueue(MockResponse().setBody(content))
        } else {
            server.enqueue(MockResponse().setBody(""))
        }
    }
}
