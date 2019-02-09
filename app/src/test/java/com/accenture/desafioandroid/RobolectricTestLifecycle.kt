package com.accenture.desafioandroid

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import com.accenture.desafioandroid.presentation.activities.MainActivity
import org.junit.Before
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController
import org.robolectric.RuntimeEnvironment
import android.content.Intent
import org.junit.After
import org.junit.Test
import android.os.Bundle

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class RobolectricTestLifecycle {

    private var myController: ActivityController<MainActivity>? = null
    private var activity: MainActivity? = null

    @Before
    fun setUp() {
        // Call the "buildActivity" method so we get an ActivityController which we can use
        // to have more control over the activity lifecycle
        myController = Robolectric.buildActivity(MainActivity::class.java)
    }

    // Activity creation that allows intent extras to be passed in
    private fun createWithIntent(extra: String) {
        val intent = Intent(RuntimeEnvironment.application, MainActivity::class.java)
        intent.putExtra("testing", extra)
        activity = myController!!
            .withIntent(intent)
            .create()
            .start()
            .resume()
            .visible()
            .get()
    }

    @Test
    fun createsAndDestroysActivity() {
        createWithIntent("my testing")
    }

    @Test
    fun createsDestectinCallsActivity() {
        createWithIntent("my testing")
        myController!!.pause().resume()

    }

    @Test
    fun recreatesRotationActivity() {
        val bundle = Bundle()

        // Destroy the original activity
        myController!!
            .saveInstanceState(bundle)
            .pause()
            .stop()
            .destroy()

        // Bring up a new activity
        myController = Robolectric.buildActivity(MainActivity::class.java)
            .create(bundle)
            .start()
            .restoreInstanceState(bundle)
            .resume()
            .visible()
        activity = myController!!.get()

    }

    @After
    fun tearDown() {
        // Destroy activity after every test
        myController!!
            .pause()
            .stop()
            .destroy()
    }
}
