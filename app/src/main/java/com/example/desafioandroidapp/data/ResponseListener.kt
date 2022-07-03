package com.example.desafioandroidapp.data

import com.example.desafioandroidapp.data.dto.*

interface ResponseListener<T> {

    fun onResponse(response: Repository)

    fun onError(repositoryError: RepositoryError)

}