package com.uharris.desafio_android.domain.models.reponse

import com.uharris.desafio_android.domain.models.Repository

data class RepositoryResponse(val total: Int = 0,
                              val items: MutableList<Repository> = mutableListOf())