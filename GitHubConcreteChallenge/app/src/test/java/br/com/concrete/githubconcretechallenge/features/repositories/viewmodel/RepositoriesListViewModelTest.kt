package br.com.concrete.githubconcretechallenge.features.repositories.viewmodel

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import br.com.concrete.githubconcretechallenge.BaseTest
import br.com.concrete.githubconcretechallenge.features.repositories.datasource.RepositoriesListDataSourceFactory
import br.com.concrete.githubconcretechallenge.features.repositories.datasource.RepositoriesListRemoteDataSource
import br.com.concrete.githubconcretechallenge.features.repositories.model.RepositoryModel
import br.com.concrete.githubconcretechallenge.features.repositories.model.UserModel
import io.reactivex.Single
import org.junit.After
import org.junit.Assert
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

class RepositoriesListViewModelTest : BaseTest() {

    private val viewModel: RepositoriesListViewModel by inject()

    private lateinit var mockDataSource: RepositoriesListRemoteDataSource

    @Before
    fun setUp() {
        val mockModule = module(override = true) {
            single { mock(RepositoriesListRemoteDataSource::class.java) }
            factory { RepositoriesListDataSourceFactory(get()) }
            viewModel { RepositoriesListViewModel(get()) }
        }

        startKoin { modules(mockModule) }
        mockDataSource = get()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    private fun createResponse(userModel: UserModel, numberOfRepositories: Int = 10): List<RepositoryModel> {
        val list = mutableListOf<RepositoryModel>()

        for (i in 1..numberOfRepositories) {
            list.add(
                RepositoryModel(
                    i.toLong(), "name$i", "description$i", "$i", "$i", userModel
                )
            )
        }

        return list
    }

    @Test
    fun `getRepositories with success and check if liveData was updated correctly`() {
        val userModel = getUserModelMock()

        `when`(
            mockDataSource.getRepositoriesList(1)
        ).thenReturn(
            Single.just(
                createResponse(userModel)
            )
        )

        val liveData = viewModel.getRepositories()

        val observer = Observer<PagedList<RepositoryModel>> {}
        try {
            liveData.observeForever(observer)

            val i = 1
            val expectedResult = RepositoryModel(
                i.toLong(), "name$i", "description$i", "$i", "$i", userModel
            )

            Assert.assertEquals(expectedResult, liveData.value?.get(0))
            Assert.assertEquals(10, liveData.value?.size)
        } finally {
            liveData.removeObserver(observer)
        }

    }


}