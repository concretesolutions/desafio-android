package com.gdavidpb.github

import com.gdavidpb.github.data.model.api.PullEntry
import com.gdavidpb.github.data.model.api.RepositoryEntry
import com.gdavidpb.github.data.model.api.SearchResultEntry
import com.gdavidpb.github.data.model.api.UserEntry
import com.gdavidpb.github.data.model.database.EmbeddedUser
import com.gdavidpb.github.data.model.database.PullEntity
import com.gdavidpb.github.data.model.database.RepositoryEntity
import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.model.Repository
import com.gdavidpb.github.domain.model.SearchResult
import com.gdavidpb.github.domain.model.User
import com.gdavidpb.github.presentation.model.PullItem
import com.gdavidpb.github.presentation.model.RepositoryItem
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
    private val databaseUser: EmbeddedUser by inject()

    @Test
    fun `should map user model from api to domain`() {
        val mappedUser = apiUser.toUser()

        assertEquals(domainUser, mappedUser)
    }

    @Test
    fun `should map repository model from api to domain`() {
        val apiRepository = RepositoryEntry(
            id = "123",
            name = "sample",
            url = "https://github.com",
            description = "description",
            full_name = "${apiUser.login}/sample",
            owner = apiUser,
            stargazers_count = 1,
            watchers_count = 1,
            open_issues_count = 1,
            forks_count = 1,
            created_at = Date(0).toISO8601(),
            updated_at = Date(0).toISO8601()
        )

        val domainRepository = Repository(
            id = "123",
            name = "sample",
            url = "https://github.com",
            description = "description",
            fullName = "${domainUser.login}/sample",
            owner = domainUser,
            stargazersCount = 1,
            watchersCount = 1,
            openIssuesCount = 1,
            forksCount = 1,
            createdAt = Date(0),
            updatedAt = Date(0),
            page = 1
        )

        val mappedRepository = apiRepository.toRepository(1)

        assertEquals(domainRepository, mappedRepository)
    }

    @Test
    fun `should map pull model from api to domain`() {
        val apiPull = PullEntry(
            node_id = "123",
            title = "title",
            body = "body",
            number = 1,
            html_url = "https://github.com",
            user = apiUser,
            created_at = Date(0).toISO8601(),
            updated_at = Date(0).toISO8601(),
            closed_at = null,
            merged_at = null
        )

        val domainPull = Pull(
            id = "123",
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
            id = "123",
            name = "sample",
            url = "https://github.com",
            description = "description",
            full_name = "${apiUser.login}/sample",
            owner = apiUser,
            stargazers_count = 1,
            watchers_count = 1,
            open_issues_count = 1,
            forks_count = 1,
            created_at = Date(0).toISO8601(),
            updated_at = Date(0).toISO8601()
        )

        val domainRepository = Repository(
            id = "123",
            name = "sample",
            fullName = "${domainUser.login}/sample",
            url = "https://github.com",
            description = "description",
            owner = domainUser,
            stargazersCount = 1,
            watchersCount = 1,
            openIssuesCount = 1,
            forksCount = 1,
            createdAt = Date(0),
            updatedAt = Date(0),
            page = 1
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

        val mappedSearchResult = apiSearchResult.toRepositorySearchResult(1)

        assertEquals(domainSearchResult, mappedSearchResult)
    }

    @Test
    fun `should map user model from database to domain`() {
        val mappedUser = databaseUser.toUser()

        assertEquals(domainUser, mappedUser)
    }

    @Test
    fun `should map pull model from database to domain`() {
        val databasePull = PullEntity(
            id = "123",
            title = "title",
            body = "body",
            repository = "sample",
            number = 1,
            url = "https://github.com",
            user = databaseUser,
            createdAt = Date(0).time,
            updatedAt = Date(0).time,
            closedAt = -1,
            mergedAt = -1
        )

        val domainPull = Pull(
            id = "123",
            title = "title",
            body = "body",
            number = 1,
            url = "https://github.com",
            user = domainUser,
            createdAt = Date(0),
            updatedAt = Date(0)
        )

        val mappedPull = databasePull.toPull()

        assertEquals(domainPull, mappedPull)
    }

    @Test
    fun `should map user model from domain to database`() {
        val mappedUser = domainUser.toEmbeddedUser()

        assertEquals(databaseUser, mappedUser)
    }

    @Test
    fun `should map repository model from domain to database`() {
        val domainRepository = Repository(
            id = "123",
            name = "sample",
            url = "https://github.com",
            description = "description",
            fullName = "${apiUser.login}/sample",
            owner = domainUser,
            stargazersCount = 1,
            watchersCount = 1,
            openIssuesCount = 1,
            forksCount = 1,
            createdAt = Date(0),
            updatedAt = Date(0),
            page = 1
        )

        val databaseRepository = RepositoryEntity(
            id = "123",
            name = "sample",
            url = "https://github.com",
            description = "description",
            fullName = "${domainUser.login}/sample",
            owner = databaseUser,
            stargazersCount = 1,
            watchersCount = 1,
            openIssuesCount = 1,
            forksCount = 1,
            createdAt = Date(0).time,
            updatedAt = Date(0).time,
            page = 1
        )

        val mappedRepository = domainRepository.toRepositoryEntity()

        assertEquals(databaseRepository, mappedRepository)
    }

    @Test
    fun `should map pull model from domain to database`() {
        val domainPull = Pull(
            id = "123",
            title = "title",
            body = "body",
            number = 1,
            url = "https://github.com",
            user = domainUser,
            createdAt = Date(0),
            updatedAt = Date(0),
            closedAt = Date(-1),
            mergedAt = Date(-1)
        )

        val databasePull = PullEntity(
            id = "123",
            title = "title",
            body = "body",
            repository = "sample",
            number = 1,
            url = "https://github.com",
            user = databaseUser,
            createdAt = Date(0).time,
            updatedAt = Date(0).time,
            mergedAt = Date(-1).time,
            closedAt = Date(-1).time
        )

        val mappedPull = domainPull.toPullEntity("sample")

        assertEquals(databasePull, mappedPull)
    }

    @Test
    fun `should map repository model from database to presentation`() {
        val databaseRepository = RepositoryEntity(
            id = "123",
            name = "sample",
            url = "https://github.com",
            description = "description",
            fullName = "${apiUser.login}/sample",
            owner = databaseUser,
            stargazersCount = 1000,
            watchersCount = 2000,
            openIssuesCount = 3000,
            forksCount = 4000,
            createdAt = Date(0).time,
            updatedAt = Date(0).time,
            page = 1
        )

        val presentationRepository = RepositoryItem(
            id = "123",
            name = "sample",
            url = "https://github.com",
            description = "description",
            fullName = "${domainUser.login}/sample",
            userLogin = databaseUser.login,
            userUrl = databaseUser.url,
            userAvatarUrl = databaseUser.avatarUrl,
            stargazersCount = "1k",
            watchersCount = "2k",
            openIssuesCount = "3k",
            forksCount = "4k",
            createdAt = "31 Dec 1969",
            updatedAt = "31 Dec 1969",
            page = 1
        )

        val mappedRepository = databaseRepository.toRepositoryItem()

        assertEquals(presentationRepository, mappedRepository)
    }

    @Test
    fun `should map pull model from domain to presentation`() {
        val domainPull = Pull(
            id = "123",
            title = "title",
            body = "body",
            number = 1,
            url = "https://github.com",
            user = domainUser,
            createdAt = Date(0),
            updatedAt = Date(0),
            closedAt = Date(-1),
            mergedAt = Date(-1)
        )

        val presentationPull = PullItem(
            id = "123",
            title = "title",
            body = "body",
            number = 1,
            url = "https://github.com",
            userLogin = domainUser.login,
            userUrl = domainUser.url,
            userAvatarUrl = domainUser.avatarUrl,
            createdAt = "31 Dec 1969",
            updatedAt = "31 Dec 1969",
            mergedAt = "-",
            closedAt = "-"
        )

        val mappedPull = domainPull.toPullItem()

        assertEquals(presentationPull, mappedPull)
    }

    @After
    fun `stop koin`() {
        StandAloneContext.stopKoin()
    }
}