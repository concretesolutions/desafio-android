package com.gdavidpb.github

import com.gdavidpb.github.data.source.remote.GitHubApi
import com.gdavidpb.github.utils.await
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest

class GitHubApiTest : KoinTest {

    private val api: GitHubApi by inject()

    @Before
    fun `start koin`() {
        StandAloneContext.startKoin(listOf(testModule))
    }

    @Test
    fun `should get repositories from GitHub api`() {
        val repositories = runBlocking {
            api.getRepositories(page = 1).await()
        }

        Assert.assertNotNull(repositories); requireNotNull(repositories)

        Assert.assertTrue(repositories.total_count > 0)
    }

    @Test
    fun `should get pull requests from first repository`() {
        val repositories = runBlocking {
            api.getRepositories(page = 1).await()
        }

        Assert.assertNotNull(repositories); requireNotNull(repositories)

        Assert.assertTrue(repositories.total_count > 0)

        val firstRepository = repositories.items.firstOrNull()

        Assert.assertNotNull(firstRepository); requireNotNull(firstRepository)

        val pulls = runBlocking {
            api.getPulls(repositoryName = firstRepository.full_name).await()
        }

        Assert.assertNotNull(pulls); requireNotNull(pulls)

        /* A repository could has not pull requests */
        Assert.assertTrue(pulls.size >= 0)
    }

    @After
    fun `stop koin`() {
        StandAloneContext.stopKoin()
    }
}
