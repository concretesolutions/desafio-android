package com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.actions

import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses.GitRepositoryItemsResponses
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses.GitRepositoryOwnerResponses
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses.GitRepositoryResponses
import com.hotmail.fignunes.skytestefabionunes.model.GitRepository
import com.hotmail.fignunes.skytestefabionunes.model.GitRepositoryItem
import com.hotmail.fignunes.skytestefabionunes.model.GitRepositoryOwner
import org.junit.Assert
import org.junit.Test

class ResponsesToGitResository {

    @Test
    fun `should return gitRepository`() {

        val gitRepository = GitRepository(
            1,
            false,
            items = listOf(
                GitRepositoryItem(
                    121395510,
                    "CS-Notes",
                    "CyC2018/CS-Notes",
                    GitRepositoryOwner(
                        "CyC2018",
                        36260787,
                        "https://avatars3.githubusercontent.com/u/36260787?v=4",
                        "https://api.github.com/users/CyC2018",
                        "",
                        ""
                    ),
                    ":books: 技术面试必备基础知识、Leetcode、计算机操作系统、计算机网络、系统设计、Java、Python、C++",
                    100424,
                    32674
                )
            )
        )

        val gitRepositoryResponses = GitRepositoryResponses(
            1,
            false,
            items = listOf(
                GitRepositoryItemsResponses(
                    121395510,
                    "CS-Notes",
                    "CyC2018/CS-Notes",
                    GitRepositoryOwnerResponses(
                        "CyC2018",
                        36260787,
                        "https://avatars3.githubusercontent.com/u/36260787?v=4",
                        "https://api.github.com/users/CyC2018"
                    ),
                    ":books: 技术面试必备基础知识、Leetcode、计算机操作系统、计算机网络、系统设计、Java、Python、C++",
                    100424,
                    32674
                )
            )
        )

        Assert.assertEquals(gitRepository, ResponsesToGitRepository().execute(gitRepositoryResponses))
    }
}