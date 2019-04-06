package com.example.desafioandroid.api

import com.example.desafioandroid.schemas.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

abstract class ServicesCallback<T> : Callback<T> {

    //ServicesCallback permite darle la estructura deseada a las respuesta que vienen de los servicios
    //para mantener una misma estructura para el manejo de errores en el código

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccess(response.body()!!, response.code())
        }
        else
        {
            var error = ErrorResponse()
            val om = ObjectMapper()
            try {
                error = om.readValue<ErrorResponse>(response.errorBody()!!.string(), ErrorResponse::class.java)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            error.status = response.code()
            onError(error)
        }
    }



    override fun onFailure(call: Call<T>, throwable: Throwable) {
        val error = ErrorResponse()

        error.message = throwable.message.toString()
        error.error = throwable.localizedMessage.toString()
        error.status = 0
        onError(error)
    }

    abstract fun onSuccess(response: T, code : Int)

    abstract fun onError(body: ErrorResponse)
}