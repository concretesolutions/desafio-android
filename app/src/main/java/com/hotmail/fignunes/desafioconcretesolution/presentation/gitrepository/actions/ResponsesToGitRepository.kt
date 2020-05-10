package com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.actions

import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses.GitRepositoryResponses
import com.hotmail.fignunes.skytestefabionunes.model.GitRepository
import com.hotmail.fignunes.skytestefabionunes.model.GitRepositoryItem
import com.hotmail.fignunes.skytestefabionunes.model.GitRepositoryOwner

class ResponsesToGitRepository {

    fun execute(responses: GitRepositoryResponses): GitRepository {
        return GitRepository(
            responses.total_count,
            responses.incomplete_results,
            responses.items.map {
                GitRepositoryItem(
                    it.id,
                    it.name,
                    it.full_name,
                    GitRepositoryOwner(
                        it.owner.login,
                        it.owner.id,
                        it.owner.avatar_url,
                        it.owner.url,
                        "",
                        ""
                    ),
                    it.description,
                    it.stargazers_count,
                    it.forks_count
                )
            }
        )
    }
}

