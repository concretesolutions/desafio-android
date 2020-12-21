package com.ccortez.desafioconcrete

//import com.nhaarman.mockitokotlin2.whenever

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.ccortez.desafioconcrete.GithubApp
import com.ccortez.desafioconcrete.R
import com.ccortez.desafioconcrete.data.remote.ApiService
import com.ccortez.desafioconcrete.model.Repositories
import com.ccortez.desafioconcrete.ui.main.MainActivity
import com.ccortez.desafioconcrete.ui.main.MainViewModel
import com.ccortez.desafioconcrete.utils.ApiUrls
import com.ccortez.desafioconcrete.utils.MyViewAction.clickOnViewChild
import com.ccortez.desafioconcrete.utils.RecyclerViewItemCountAssertion
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit.rule
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@MediumTest
@RunWith(AndroidJUnit4::class)
class RepositoryListFragmentTest {

    @get:Rule
    val exceptionRule = ExpectedException.none()

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(
        MainActivity::class.java,
        true, false
    )

    private lateinit var context: Context
    private lateinit var application: Application
    private lateinit var viewModel: MainViewModel
    private val repoLiveData = MutableLiveData<Repositories>()
    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: ApiService
    private val SERVER_PORT = 1234

    @Before
    fun setup() {
//        context = ApplicationProvider.getApplicationContext<Context>()
        context = InstrumentationRegistry.getInstrumentation().targetContext
        application =
            InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext as GithubApp

        mockWebServer = MockWebServer()
        mockWebServer!!.dispatcher = dispatcher
        mockWebServer!!.start(SERVER_PORT)
        ApiUrls.API_BASE_URL = mockWebServer!!.url("/").toString()
        InstrumentationRegistry.registerInstance(
            InstrumentationRegistry.getInstrumentation(),
            Bundle()
        )

//        activityRule.launchActivity(null)
//        activityRule.launchActivity(Intent())
    }

    @Before
    fun createService() {

    }

    @Before
    fun init() {
//        viewModel = mock(RepositoryListViewModel::class.java)


    }

    @After
    fun teardown() {
//        db.close()
        mockWebServer.shutdown()
    }

    @Test
    fun repositoryListTest() {
//        mockWebServer.enqueue(
//            MockResponse().setBody(
//            loadJSON("api-response/"+"search.json")
////                context.assets.open("repos-yigit.json") as String
//            )
//        )

        activityRule.launchActivity(Intent())

        val expected = 30

        Thread.sleep(7000);

        onView(withId(R.id.repo_list))
            .check(
                RecyclerViewItemCountAssertion(expected)
            )


    }

    @Test
    fun enterFirstItemTest() {

//        mockWebServer.enqueue(
//            MockResponse().setBody(
//                loadJSON("api-response/"+"search.json")
////                context.assets.open("repos-yigit.json") as String
//            )
//        )

        activityRule.launchActivity(Intent())

        val expected = 7

        Thread.sleep(5000);

        onView(withId(R.id.repo_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, clickOnViewChild(R.id.repository_item)))

        val _currentActivity :Activity = activityRule.activity

        println("current Fragment: "
                +getVisibleFragment(_currentActivity)
        )

        Thread.sleep(5000);

        onView(withId(R.id.pulls_items))
            .check(
                RecyclerViewItemCountAssertion(expected)
            )

//        onView(withId(R.id.pulls_items))
//            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, clickOnViewChild(R.id.repository_item)))

    }

    @Test
    fun enterSecondItemTest() {

        activityRule.launchActivity(Intent())

        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val expected = 1

        Thread.sleep(5000);

        onView(withId(R.id.repo_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, clickOnViewChild(R.id.repository_item)))

        val _currentActivity :Activity = activityRule.activity

        println("current Fragment: "
                +getVisibleFragment(_currentActivity)
        )

        Thread.sleep(5000);

        onView(withId(R.id.pulls_items))
            .check(
                RecyclerViewItemCountAssertion(expected)
            )

        onView(withId(R.id.tv_empty_pull_list))
            .check(
                matches(
                    withText(
                        appContext.getString(R.string.empty_pulls)
                    )
                )
            )
    }

    fun getVisibleFragment(activity: Activity): android.app.Fragment? {
        val fragmentManager: android.app.FragmentManager? =
            activity.getFragmentManager()
        val fragments: MutableList<android.app.Fragment>? =
            fragmentManager?.getFragments()
        if (fragments != null) {
            for (fragment in fragments) {
                if (fragment != null && fragment.isVisible()) return fragment
            }
            return null
        } else
            return null
    }

    private val dispatcher: Dispatcher = object : Dispatcher() {
        @Throws(InterruptedException::class)
        override fun dispatch(request: RecordedRequest): MockResponse {
            System.out.println("------------- request.path: " + request.path)
            if (request.path == "/search/repositories?q=language:Java&sort=stars&page=1") {
//                val fileName = "repos-yigit.json"
                val fileName = "search.json"
                try {
                    return MockResponse().setResponseCode(200)
                        .setBody(
//                            RestServiceTestHelper.getStringFromFile(context,
//                                fileName)
                            loadJSON("api-response/"+fileName)
                        )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (request.path == "https://api.github.com/repos/CyC2018/CS-Notes/pulls") {
                val fileName = "repos-yigit.json"
                try {
                    return MockResponse().setResponseCode(200)
                        .setBody(
//                            RestServiceTestHelper.getStringFromFile(context,
//                            fileName)
                            loadJSON("api-response/"+fileName)
                        )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return MockResponse().setResponseCode(404)
        }
    }

    @Throws(IOException::class)
    fun loadJSON(fileName: String): String {
        var inputStream: InputStream? = null
        try {
            inputStream =
                javaClass.classLoader?.getResourceAsStream(fileName)
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream))

            var str: String? = reader.readLine()
            while (str != null) {
                builder.append(str)
                str = reader.readLine()
            }
            return builder.toString()
        } finally {
            inputStream?.close()
        }
    }

}