package br.com.bernardino.githubsearch

import br.com.bernardino.githubsearch.model.Repository

interface ClickReposListener {
    fun onClick (repository : Repository)
}