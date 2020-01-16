package com.concretesolutions.desafioandroid.helpers

import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit

class NetworkHelperUnitTest {

    @Test
    fun getRetrofitInstance_isCorrect() {
        val retrofitInstance = NetworkHelper.getRetrofitInstance("http://teste/api/")
        Assert.assertNotNull("Must create an object", retrofitInstance)
        Assert.assertTrue( "Must create a instance of Retrofit", retrofitInstance is Retrofit)
    }

    @Test
    fun getRetrofitInstanceGitHub_isCorrect() {
        val retrofitInstance = NetworkHelper.getRetrofitInstanceGitHub()
        Assert.assertNotNull("Must create an object", retrofitInstance)
        Assert.assertTrue("Must create a instance of Retrofit", retrofitInstance is Retrofit)
    }
}