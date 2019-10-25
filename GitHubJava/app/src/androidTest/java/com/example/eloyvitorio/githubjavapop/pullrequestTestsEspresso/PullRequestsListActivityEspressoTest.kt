package com.example.eloyvitorio.githubjavapop.pullrequestTestsEspresso

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.eloyvitorio.githubjavapop.R
import com.example.eloyvitorio.githubjavapop.data.network.CreateRetrofitImpl
import com.example.eloyvitorio.githubjavapop.ui.pullrequest.PullRequestsListActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.BufferedReader
import java.io.InputStream

@RunWith(AndroidJUnit4::class)
class PullRequestsListActivityEspressoTest {
    private val server = MockWebServer()

    private val testContext: Context = InstrumentationRegistry.getInstrumentation().getContext()

    @get:Rule
    val rule = IntentsTestRule(
            PullRequestsListActivity::class.java, true, false)

    @Before
    fun setUp() {
        server.start()
        val rootUrl = server.url("/").toString()
        CreateRetrofitImpl.createApi(rootUrl)
    }

    @Test
    fun checkOnPullRequestList_fieldsAreDisplayed() {
        createMockOfPullRequestList(create = true, emptyResponse = false)
        rule.launchActivity(getIntentForTest())

        pullRequestListItem {
            repositoryTitleIsDisplayed("Merge pull request #1 from CyC2018/master")
            repositoryBodyIsDisplayed("synch")
            repositoryUserNameIsDisplayed("zhangj311")
            repositoryTrueNameIsDisplayed("zhangj311/Interview-Notebook")
        }
    }

    @Test
    fun checkOnPullRequestList_imagesAreDisplayed() {
        createMockOfPullRequestList(create = true, emptyResponse = false)
        rule.launchActivity(getIntentForTest())

        pullRequestListItem {
            userPhotoIsDisplayed(R.id.recyclerViewPullRequestsList, 0,
                    R.id.imageViewPrUserPhoto)
        }
    }

    @Test
    fun checkOnPullRequestList_goToPullRequestPage() {
        createMockOfPullRequestList(create = true, emptyResponse = false)
        rule.launchActivity(getIntentForTest())

        pullRequestListItem {
            clickOnPositionInList(R.id.recyclerViewPullRequestsList, 0)
            intendedPullRequestPage(
                    Intent.ACTION_VIEW,
                    "https://github.com/CyC2018/CS-Notes/pull/806",
                    "Escolha o navegador para abrir este endereço:",
                    Intent.ACTION_CHOOSER)
        }
    }

    @Test
    fun checkOnPullRequestList_errorFlowIsCalled() {
        val messageError = "Um erro ocorreu. \nTente carregar novamente."
        createMockOfPullRequestList(create = false, emptyResponse = false)
        rule.launchActivity(getIntentForTest())

        pullRequestListItem {
            imageErrorLoadIsDisplayed(R.id.imageViewErrorLoad)
            messageErrorLoadIsDisplayed(messageError)
            buttonErrorLoadIsClickable(R.id.buttonErrorLoadRetry)
        }
    }

    @Test
    fun checkOnPullRequestList_EmptyStateIsCalled() {
        val messageEmpty = "Não há Pull Requests para este repositório."
        createMockOfPullRequestList(create = true, emptyResponse = true)
        rule.launchActivity(getIntentForTest())

        pullRequestListItem {
            imageEmptyListIsDisplayed(R.id.imageViewPullRequestListEmpty)
            messageEmptyListIsDisplayed(messageEmpty)
        }
    }

    private fun createMockOfPullRequestList(create: Boolean, emptyResponse: Boolean) {
        val inputStream: InputStream = testContext.assets.open("pullrequests_response_p1.json")
        val content = inputStream.bufferedReader().use(BufferedReader::readText)

        if (create && !emptyResponse) {
            server.enqueue(MockResponse().setBody(content))
        } else if (create && emptyResponse) {
            server.enqueue(MockResponse().setBody("[]"))
        } else if (!create && !emptyResponse) {
            server.enqueue(MockResponse().setBody(""))
        }
    }

    private fun getIntentForTest(): Intent {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return PullRequestsListActivity.newIntent(
                context, "CyC2018", "CS-Notes")
    }
}