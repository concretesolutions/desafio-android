package com.pedrenrique.githubapp.features.repositories

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.pedrenrique.githubapp.R
import com.pedrenrique.githubapp.config.MOCK_SERVER_DOMAIN
import com.pedrenrique.githubapp.config.MOCK_SERVER_PORT
import com.pedrenrique.githubapp.mock.MockDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
@LargeTest
class RepositoriesFragmentTest {

    private val context = InstrumentationRegistry.getInstrumentation().context
    private val server = MockWebServer()

    @Before
    fun setUp() {
        server.apply {
            start(MOCK_SERVER_PORT)
            url(MOCK_SERVER_DOMAIN)
            setDispatcher(MockDispatcher(context))
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun shouldLoadRepositories() {
        // Create a mock NavController
        val mockNavController = mock(NavController::class.java)

        // Create a graphical FragmentScenario for the TitleScreen
        val scenario = launchFragmentInContainer<RepositoriesFragment>()

        // Set the NavController property on the fragment
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        onView(withId(R.id.rvRepositories))
            .check(matches(isDisplayed()))

        onView(withId(R.id.layoutLoading))
            .check(matches(isDisplayed()))

//        onView(withId(R.id.layoutRepository))
//            .check(matches(isDisplayed()))
    }
}