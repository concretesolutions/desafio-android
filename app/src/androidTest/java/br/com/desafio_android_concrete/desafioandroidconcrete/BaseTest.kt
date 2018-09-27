package br.com.desafio_android_concrete.desafioandroidconcrete

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import br.com.desafio.concrete.di.AppModule
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext
import org.koin.standalone.StandAloneContext.stopKoin


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
open class BaseTest  {

    @Before
    fun before(){
        val context  = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        stopKoin()
        StandAloneContext.startKoin(listOf(AppModule.getModule(context)))
    }

    @After
    fun after(){
        stopKoin()
    }



    fun delay(){
        Thread.sleep(2500)
    }
}
