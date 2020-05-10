package com.hotmail.fignunes.skytestefabionunes.repository.remote

import com.hotmail.fignunes.desafioconcretesolution.repository.remote.RemoteFactory
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.RemoteGitRepositoryRepository
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.services.GitRepositoryServices
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.RemotePullRequestRepository
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.resources.RemotePullRequestResources
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.services.PullRequestServices
import com.hotmail.fignunes.skytestefabionunes.repository.remote.gitrepository.resources.RemoteGitRepositoryResources
import org.koin.core.KoinComponent
import org.koin.core.inject

class RemoteRepository : RemoteFactory, KoinComponent {

    private val gitRepositoryServices: GitRepositoryServices by inject()
    private val pullRequestServices: PullRequestServices by inject()

    override val gitRepository: RemoteGitRepositoryResources = RemoteGitRepositoryRepository(gitRepositoryServices)
    override val pullRequest: RemotePullRequestResources = RemotePullRequestRepository(pullRequestServices)
}