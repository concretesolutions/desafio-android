package com.jsouza.repocatalog.domain.usecase

import com.jsouza.repocatalog.domain.repository.CatalogRepository

class FetchRepositoriesFromApi(private val catalogRepository: CatalogRepository) {

    suspend operator fun invoke(page: Int) = catalogRepository.refreshRepos(page)
}
