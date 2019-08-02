package br.com.concrete.githubconcretechallenge.features.repositories.model

data class RepositoriesListResponse(val totalCount : Long = 0,
                                    val incompleteResults: Boolean = false,
                                    val items: List<RepositoryModel>)


