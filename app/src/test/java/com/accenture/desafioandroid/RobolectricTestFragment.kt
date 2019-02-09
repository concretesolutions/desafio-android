package com.accenture.desafioandroid

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import com.accenture.desafioandroid.presentation.activities.MainActivity
import com.accenture.desafioandroid.presentation.fragment.HomeFragment
import com.accenture.desafioandroid.presentation.fragment.PullRequestFragment
import com.accenture.desafioandroid.presentation.fragment.WelcomeFragment
import org.junit.Before
import org.robolectric.Robolectric


import org.junit.Assert.*
import org.junit.Test
import org.robolectric.util.FragmentTestUtil.startFragment

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class RobolectricTestFragment {


    @Before
    @Throws(Exception::class)
    fun setUp() {
        val activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .start()
            .resume()
            .get()

        val homeFragment = HomeFragment()
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(homeFragment, null)
        fragmentTransaction.commit()

    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        val fragment = WelcomeFragment.newInstance()
        startFragment(fragment)
        assertNotNull(fragment)
    }

}