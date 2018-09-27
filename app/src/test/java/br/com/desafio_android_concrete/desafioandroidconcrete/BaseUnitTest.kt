package br.com.desafio_android_concrete.desafioandroidconcrete

import android.app.Instrumentation
import android.test.mock.MockContext
import br.com.desafio.concrete.di.AppModule
import org.junit.After
import org.junit.Before
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
open class BaseUnitTest : Instrumentation(), KoinTest {


    @Before
    open fun before(){
        MockitoAnnotations.initMocks(this)
        val context = MockContext()
        startKoin( listOf(AppModule.getModule(context)))
    }

    @After
    fun after(){
        stopKoin()
    }

}
