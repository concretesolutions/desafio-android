package com.concretesolutions.desafioandroid.helpers

import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit

class StringHelperUnitTest {

    @Test
    fun makeShortDescription_isCorrect() {
        val longText = StringHelper.makeShortDescription("Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia, voluptates? Nisi, delectus pariatur impedit, nostrum numquam beatae ex tempore excepturi assumenda laboriosam dolorum illum doloribus sequi, deserunt eum. Veniam, inventore.")
        Assert.assertTrue("Must have tre points", longText.contains("..."))
        Assert.assertTrue( "Must have 73 characters", longText.length == 73)
    }

    @Test
    fun prepareDescription_isCorrect() {
        val testText = "Lorem ipsum dolor sit amet consectetur adipisicing"
        Assert.assertTrue("Must return a text when null", StringHelper.prepareDescription(null).equals(StringHelper.NODESCRIPTION))
        Assert.assertTrue("Must return the same text", StringHelper.prepareDescription(testText).equals(testText))
    }
}