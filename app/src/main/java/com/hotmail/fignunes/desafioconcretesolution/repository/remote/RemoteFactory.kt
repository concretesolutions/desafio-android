package com.hotmail.fignunes.desafioconcretesolution.repository.remote

import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.resources.RemotePullRequestResources
import com.hotmail.fignunes.skytestefabionunes.repository.remote.gitrepository.resources.RemoteGitRepositoryResources

interface RemoteFactory {
    val gitRepository: RemoteGitRepositoryResources
    val pullRequest: RemotePullRequestResources
}