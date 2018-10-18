package com.example.consultor.testacc

import android.app.Application
import android.content.Context
import com.example.consultor.testacc.utils.MyConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class TestACApp: Application() {
    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    init {
        instance = this
    }

    companion object {
        private var instance: TestACApp? = null
         var retrofit: Retrofit?=null


        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()
        val context: Context = TestACApp.applicationContext()
        retrofit = Retrofit.Builder()
            .baseUrl(MyConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
            .build()

    }
}