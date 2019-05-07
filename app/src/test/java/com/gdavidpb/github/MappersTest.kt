package com.gdavidpb.github

import com.gdavidpb.github.data.model.api.PullEntry
import com.gdavidpb.github.data.model.api.RepositoryEntry
import com.gdavidpb.github.data.model.api.SearchResultEntry
import com.gdavidpb.github.data.model.api.UserEntry
import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.model.Repository
import com.gdavidpb.github.domain.model.SearchResult
import com.gdavidpb.github.domain.model.User
import com.gdavidpb.github.utils.*
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import java.util.*

class MappersTest : KoinTest {

    @Before
    fun `start koin`() {
        StandAloneContext.startKoin(listOf(testModule))
    }

    /* api to domain models mappers */

    private val domainUser: User by inject()
    private val apiUser: UserEntry by inject()

    @Test
    fun `should map user model from api to domain`() {
        val mappedUser = apiUser.toUser()

        assertEquals(domainUser, mappedUser)
    }

    @Test
    fun `should map repository model from api to domain`() {
        val apiRepository = RepositoryEntry(
            id = 1,
            name = "sample",
            url = "https://github.com",
            description = "description",
            owner = apiUser,
            watchers_count = 1,
            open_issues_count = 1,
            forks_count = 1,
            created_at = Date(0).toISO8601(),
            updated_at = Date(0).toISO8601()
        )

        val domainRepository = Repository(
            id = 1,
            name = "sample",
            url = "https://github.com",
            description = "description",
            owner = domainUser,
            watchersCount = 1,
            openIssuesCount = 1,
            forksCount = 1,
            createdAt = Date(0),
            updatedAt = Date(0)
        )

        val mappedRepository = apiRepository.toRepository()

        assertEquals(domainRepository, mappedRepository)
    }

    @Test
    fun `should map pull model from api to domain`() {
        val apiPull = PullEntry(
            id = 1,
            title = "title",
            body = "body",
            number = 1,
            url = "https://github.com",
            user = apiUser,
            created_at = Date(0).toISO8601(),
            updated_at = Date(0).toISO8601(),
            closed_at = null,
            merged_at = null
        )

        val domainPull = Pull(
            id = 1,
            title = "title",
            body = "body",
            number = 1,
            url = "https://github.com",
            user = domainUser,
            createdAt = Date(0),
            updatedAt = Date(0)
        )

        val mappedPull = apiPull.toPull()

        assertEquals(domainPull, mappedPull)
    }

    @Test
    fun `should map search result model from api to domain`() {
        val apiRepository = RepositoryEntry(
            id = 1,
            name = "sample",
            url = "https://github.com",
            description = "description",
            owner = apiUser,
            watchers_count = 1,
            open_issues_count = 1,
            forks_count = 1,
            created_at = Date(0).toISO8601(),
            updated_at = Date(0).toISO8601()
        )

        val domainRepository = Repository(
            id = 1,
            name = "sample",
            url = "https://github.com",
            description = "description",
            owner = domainUser,
            watchersCount = 1,
            openIssuesCount = 1,
            forksCount = 1,
            createdAt = Date(0),
            updatedAt = Date(0)
        )

        val apiSearchResult = SearchResultEntry(
            total_count = 1,
            incomplete_results = false,
            items = listOf(
                apiRepository
            )
        )

        val domainSearchResult = SearchResult(
            totalCount = 1,
            incompleteResults = false,
            items = listOf(
                domainRepository
            )
        )

        val mappedSearchResult = apiSearchResult.toRepositorySearchResult()

        assertEquals(domainSearchResult, mappedSearchResult)
    }

    @After
    fun `stop koin`() {
        StandAloneContext.stopKoin()
    }
}