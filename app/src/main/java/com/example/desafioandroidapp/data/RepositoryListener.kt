package com.example.desafioandroidapp.data

import com.example.desafioandroidapp.data.dto.*

interface RepositoryListener<T> {

    fun onResponse(response: Repository)

    fun onError(repositoryError: Error)

}