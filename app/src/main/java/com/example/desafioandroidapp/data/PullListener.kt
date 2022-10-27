package com.example.desafioandroidapp.data

import com.example.desafioandroidapp.data.dto.Error
import com.example.desafioandroidapp.data.dto.Pull

interface PullListener<T> {

    fun onResponsePull(response: List<Pull>)

    fun onError(repositoryError: Error)
}