package dev.theuzfaleiro.trendingongithub.ui.feature.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import androidx.paging.PagedList
import dev.theuzfaleiro.trendingongithub.ui.feature.home.datasource.RepositoryDataSourceFactory
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Owner
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Repository
import dev.theuzfaleiro.trendingongithub.ui.feature.home.repository.HomeRepository
import io.github.plastix.rxschedulerrule.RxSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private val homeRepository = mockk<HomeRepository>()

    private val repositoryDataSource = mockk<RepositoryDataSourceFactory>()

    private val repositoryPagedList = mockk<PagedList<Repository>>()

    lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {

        mockkConstructor(PagedList::class)

        every {
            anyConstructed<PagedList<Repository>>().first()
        } returns Repository(
            0,
            "Repository Name",
            "Repository Description",
            Owner("https://bit.ly", "theuzfaleiro"),
            42,
            42
        )

        homeViewModel = HomeViewModel(homeRepository)
    }

    @Test
    @Ignore
    fun shouldDisplayAvailableRepositories_WhenFetchRepositories() {
        //arrange
        every {
            homeRepository.fetchTrendingRepositories()
        } returns Observable.just(repositoryPagedList)


        //act
        homeViewModel.fetchRepositories()

         //assert
       assertEquals(
            "Repository Name",
            homeViewModel.getRepositories().value!![0]!!.name
        )
    }
}