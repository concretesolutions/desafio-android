package br.com.concrete.githubconcretechallenge.features.pullrequests.viewmodel

import androidx.lifecycle.Observer
import br.com.concrete.githubconcretechallenge.BaseTest
import br.com.concrete.githubconcretechallenge.features.pullrequests.datasource.PullRequestsDataSource
import br.com.concrete.githubconcretechallenge.features.pullrequests.model.PullRequestModel
import br.com.concrete.githubconcretechallenge.features.repositories.model.RepositoryModel
import br.com.concrete.githubconcretechallenge.features.repositories.model.UserModel
import io.reactivex.Single
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.get
import org.koin.test.inject
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class PullRequestsViewModelTest : BaseTest() {

    private val viewModel: PullRequestsViewModel by inject()

    private lateinit var mockDataSource: PullRequestsDataSource

    @Before
    fun setUp() {
        val mockModule = module(override = true) {
            single { mock(PullRequestsDataSource::class.java) }
            viewModel { PullRequestsViewModel(get()) }
        }

        startKoin { modules(mockModule) }
        mockDataSource = get()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    private fun getRepositoryModelMock(userModel: UserModel): RepositoryModel {
        val repositoryModel = mock(RepositoryModel::class.java)

        `when`(repositoryModel.owner).thenReturn(userModel)
        `when`(repositoryModel.owner.login).thenReturn("iluwatar")
        `when`(repositoryModel.name).thenReturn("java-design-patterns")

        return repositoryModel
    }

    private fun createListOfPullRequests(
        userModel: UserModel,
        numberOfPrs: Int = 10,
        status: String = "open"
    ): List<PullRequestModel> {

        val list = mutableListOf<PullRequestModel>()
        for (i in 1..numberOfPrs) {
            list.add(
                PullRequestModel(
                    i.toLong(),
                    "title$i", "body$i", "url$i", userModel, status
                )
            )
        }

        return list
    }

    @Test
    fun `loadPullRequests and count opened|closed PRs`() {
        val userModel = getUserModelMock()
        val repositoryModel = getRepositoryModelMock(userModel)

        `when`(
            mockDataSource.getPullRequests(
                "iluwatar", "java-design-patterns"
            )
        ).thenReturn(
            Single.just(
                createListOfPullRequests(userModel) + createListOfPullRequests(userModel, 12, "close")
            )
        )

        val observer = Observer<Pair<Int, Int>> {}
        try {
            viewModel.liveDataOpenedClosedPullRequestCount.observeForever(observer)

            viewModel.loadPullRequests(repositoryModel)

            assertEquals(10 to 12, viewModel.liveDataOpenedClosedPullRequestCount.value)
        } finally {
            viewModel.liveDataOpenedClosedPullRequestCount.removeObserver(observer)
        }
    }

    @Test
    fun `loadPullRequests with success and check if liveData value was set correctly`() {
        val userModel = getUserModelMock()
        val repositoryModel = getRepositoryModelMock(userModel)

        `when`(
            mockDataSource.getPullRequests(
                repositoryModel.owner.login,
                repositoryModel.name
            )
        ).thenReturn(
            Single.just(
                createListOfPullRequests(userModel)
            )
        )

        viewModel.loadPullRequests(repositoryModel)

        assertEquals(createListOfPullRequests(userModel), viewModel.liveDataPullRequestList.value)
    }

}