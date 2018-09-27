package br.com.concrete.desafio.data

object RepositoryProvider {

    val repoRepository: RepoRepository by lazy(::RepoRepositoryImpl)
    val pullRequestRepository: PullRequestRepository by lazy(::PullRequestRepositoryImpl)

}