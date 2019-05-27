package com.losingtimeapps.javahub

import com.losingtimeapps.presentation.schedule.ScheduleImp

import org.junit.Before
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val scheduleImp = ScheduleImp()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

    }

}
