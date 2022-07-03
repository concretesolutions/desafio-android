package com.desafioandroid.getirepos.data

import com.desafioandroid.getirepos.data.dto.RepositoryError
import com.desafioandroid.getirepos.data.dto.SearchResponse

interface SearchResponseListener {
    fun onSearchResponse(response: SearchResponse)
    fun onError(repositoryError: RepositoryError)

}