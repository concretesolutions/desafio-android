package com.dobler.desafio_android.data.repository.githubRepository

import com.dobler.desafio_android.data.model.GithubRepository
import com.dobler.desafio_android.util.paging.Listing
import io.reactivex.Observable

interface RepositoryContract {

    fun getPage(): Observable<Listing<GithubRepository>>

}
