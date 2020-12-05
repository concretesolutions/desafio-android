package com.ccortez.desafioconcreteapplication

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
import com.ccortez.desafioconcreteapplication.service.ApiUrls
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import com.ccortez.desafioconcreteapplication.service.repository.BackEndService
import com.ccortez.desafioconcreteapplication.service.repository.GitHubRepository
import com.ccortez.desafioconcreteapplication.utils.MyViewAction
import com.ccortez.desafioconcreteapplication.utils.MyViewAction.clickOnViewChild
import com.ccortez.desafioconcreteapplication.utils.RecyclerViewItemCountAssertion
import com.ccortez.desafioconcreteapplication.view.ui.MainActivity
import com.ccortez.desafioconcreteapplication.viewmodel.RepositoryListViewModel
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
    private lateinit var viewModel: RepositoryListViewModel
    private val repoLiveData = MutableLiveData<Repositories>()
    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: BackEndService
    private val SERVER_PORT = 1234

    @Before
    fun setup() {
//        context = ApplicationProvider.getApplicationContext<Context>()
        context = InstrumentationRegistry.getInstrumentation().targetContext
        application =
            InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext as MVVMApplication

        mockWebServer = MockWebServer()
        mockWebServer!!.setDispatcher(dispatcher)
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
    fun loadingRepositoriesTextTest() {
//        val application = Mockito.mock(MVVMApplication::class.java)
//        val application = Mockito.mock(Application::class.java)
        val application: MVVMApplication =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as MVVMApplication
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        val context = Mockito.mock(Context::class.java)

//        val repository: GitHubRepository = mock(GitHubRepository::class.java)
//        val repository = CarRepository(db)

//        whenever(repository.getCarDbList(context))
//            .thenReturn(MutableLiveData<List<Car>>())

//        whenever(application.applicationContext)
//            .thenReturn(context)

//        val expected = 3
//        val model = RepositoryListViewModel(repository, application)
//
//        db.carDao()?.insert(Car(1,"Corsa Hatch", "Chevrolet", "Carro", 1, 10000, ""))
//        db.carDao()?.insert(Car(2,"Corsa Super", "Chevrolet", "Carro", 1, 10000, ""))
//        db.carDao()?.insert(Car(3,"Corsa SW", "Chevrolet", "Carro", 1, 10000, ""))

//        println("mutableCarList: "+mutableCarList)
//        println("mutableCarList.value: "+mutableCarList.value)

//        whenever(application.applicationContext)
//            .thenReturn(
//                context
//            )

        // instantiate your fragment
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

//        val fragmentScenario
//                = launchFragmentInContainer<ShopCartListFragment>()

        activityRule.launchActivity(Intent())

        onView(withId(R.id.loading_cars))
            .check(
                matches(
                    withText(
                        appContext.getString(R.string.loading_cars)
                    )
                )
            )

//        onView(withId(R.id.car_list))
//            .check(
//                RecyclerViewItemCountAssertion(db.carDao()!!.allCars, expected)
//            )

//        val _currentActivity :Activity = activityRule.activity
//
//        println("current Fragment: "+getVisibleFragment(_currentActivity))

    }

    @Test
    fun repositoryListTest() {
/*        mockWebServer.enqueue(
            MockResponse().setBody(
            loadJSON("api-response/repos-yigit.json")
//                context.assets.open("repos-yigit.json") as String
            )
        )*/

        activityRule.launchActivity(Intent())

        val expected = 30

        Thread.sleep(5000);

        onView(withId(R.id.car_list))
            .check(
                RecyclerViewItemCountAssertion(expected)
            )


    }

    @Test
    fun enterFirstItemTest() {

        activityRule.launchActivity(Intent())

        val expected = 0

        Thread.sleep(5000);

        onView(withId(R.id.car_list))
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

        val expected = 6

        Thread.sleep(5000);

        onView(withId(R.id.car_list))
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

//        onView(withId(R.id.pulls_items))
//            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, clickOnViewChild(R.id.repository_item)))

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
        override fun dispatch(request: RecordedRequest): MockResponse? {
            System.out.println("------------- request.path: " + request.path)
            if (request.path == "/repositories?q=language:Java&sort=stars&page=1") {
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