package dev.theuzfaleiro.trendingongithub.ui.feature.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Owner
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Repository
import dev.theuzfaleiro.trendingongithub.ui.feature.home.repository.HomeRepository
import io.github.plastix.rxschedulerrule.RxSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private val homeRepository = mockk<HomeRepository>()

    private val repositoryPagedList = mockk<PagedList<Repository>>()

    lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(homeRepository)
    }

    @Test
    fun shouldDisplayAvailableRepositories_WhenFetchRepositories() {
        every {
            repositoryPagedList.first()
        } returns Repository(
            0,
            "Repository Name",
            "Repository Description",
            Owner("https://bit.ly", "theuzfaleiro"),
            42,
            42
        )

        every {
            homeRepository.fetchTrendingRepositories()
        } returns Observable.just(repositoryPagedList)

        //act
        homeViewModel.getRepositories()

        //assert
        assertEquals(
            "Repository Name",
            homeViewModel.getRepositories().value!!.first().name
        )
    }
}