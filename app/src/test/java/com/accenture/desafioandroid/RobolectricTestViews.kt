package com.accenture.desafioandroid

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File
import com.accenture.desafioandroid.presentation.activities.MainActivity
import kotlinx.android.synthetic.main.content_main.*
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import android.R
import android.widget.LinearLayout
import junit.framework.Assert.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Assert.assertEquals
import org.hamcrest.CoreMatchers.equalTo
import org.robolectric.RuntimeEnvironment


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class RobolectricTestViews {

    private var activity: MainActivity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get()
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }



    @Test
    fun clickingUpgradeButtonLaunchesUpgradeActivity() {
        val applicationId = BuildConfig.APPLICATION_ID
        if (applicationId == "com.accenture.desafioandroid") {
            // do
        }
    }


    @Test
    fun validateTextViewContent() {
        val tvHelloWorld = activity!!.tv_title
        assertNotNull("TextView could not be found", tvHelloWorld)
        assertTrue(
            "TextView contains incorrect text",
            "DesafioAndroid" == tvHelloWorld.text.toString()
        )
    }

    @Test
    @Throws(Exception::class)
    fun shouldHaveDefaultMargin() {
        val textView = activity!!.tv_title
        val bottomMargin = (textView.layoutParams as LinearLayout.LayoutParams).bottomMargin
        assertEquals(5, bottomMargin)
        val topMargin = (textView.layoutParams as LinearLayout.LayoutParams).topMargin
        assertEquals(5, topMargin)
        val rightMargin = (textView.layoutParams as LinearLayout.LayoutParams).rightMargin
        assertEquals(10, rightMargin)
        val leftMargin = (textView.layoutParams as LinearLayout.LayoutParams).leftMargin
        assertEquals(10, leftMargin)
    }


}