package br.com.bernardino.githubsearch.database

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class RepoSearchResult(
    val data: LiveData<PagedList<RepositoryDatabase>>,
    val networkErrors: LiveData<String>
)