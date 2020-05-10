package com.hotmail.fignunes.desafioconcretesolution.presentation.common.extensions

import com.hotmail.fignunes.desafioconcretesolution.common.extensions.reverseDateToBar
import org.junit.Assert.assertEquals
import org.junit.Test

class StringAmountExtKtTest {

    @Test
    fun dateReserveToDateNormalWithBar() {
        val dateReverse: String  = "2020-05-09T07:03:41Z"
        val date = "09/05/2020"

        assertEquals(date, dateReverse.reverseDateToBar())
    }
}