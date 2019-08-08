package br.com.eriberto.desafioandroidconcrete

import android.util.Log
import br.com.eriberto.desafioandroidconcrete.model.pojo.*
import br.com.eriberto.desafioandroidconcrete.model.retrofit.services.ForksService
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

class ForkServiceTest {

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

        val arrayList = ArrayList<ForkRepositorio>()
        arrayList.add(
            ForkRepositorio(
                titulo = "teste",
                descricao = "sfgs",
                autorPR = AutorPR(
                    nome = "",
                    urlFoto = "",
                    urlSite = ""
                ),
                cabecalho = Cabecalho(
                    repositorio = Repositorio(
                        nomeRepositorio = "",
                        descricaoRepositorio = "",
                        htmlSite = "",
                        proprietario = Proprietario(
                            nomeAutor = "",
                            avatar_url = ""
                        ),
                        quantidadeDeEstrelas = 0,
                        quantidadeDeForks = 0
                    )
                )
            )
        )

        mockBackend.enqueue(
            MockResponse().setBody(
                Gson().toJson(
                    arrayList
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
            .create(ForksService::class.java)

        msgApi.getForks("", "").enqueue(object : Callback<ArrayList<ForkRepositorio>> {
            override fun onFailure(call: Call<ArrayList<ForkRepositorio>>, t: Throwable) {
                assert(call.isCanceled)
            }

            override fun onResponse(call: Call<ArrayList<ForkRepositorio>>, response: Response<ArrayList<ForkRepositorio>>) {
                assertTrue(response.isSuccessful)
            }
        })
    }
}