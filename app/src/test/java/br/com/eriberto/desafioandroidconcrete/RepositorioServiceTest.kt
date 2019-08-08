package br.com.eriberto.desafioandroidconcrete

import android.util.Log
import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDTO
import br.com.eriberto.desafioandroidconcrete.model.retrofit.services.RepositoriosService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositorioServiceTest {

    lateinit var mockBackend: MockWebServer

    @Before
    fun setUp() {
        mockBackend = MockWebServer()
        mockBackend.start()
    }

    @After
    fun fim(){
        mockBackend.shutdown()
    }

    @Test
    fun test() {
        mockBackend.enqueue(
            MockResponse().setBody(
                Gson().toJson(
                    RepositorioDTO(
                        items = arrayListOf(),
                        incomplete_results = true,
                        total_count = 0
                    )
                )
            )
        )
    }

    @Test
    fun testeDeRetorno() {

        val msgApi = Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockBackend.url("/").toString())
            .build()
            .create(RepositoriosService::class.java)

        msgApi.getRepositorios(0).enqueue(object : Callback<RepositorioDTO> {
            override fun onFailure(call: Call<RepositorioDTO>, t: Throwable) {
                assert(call.isCanceled)
            }

            override fun onResponse(call: Call<RepositorioDTO>, response: Response<RepositorioDTO>) {
                assertTrue(response.isSuccessful)
            }
        })
    }
}