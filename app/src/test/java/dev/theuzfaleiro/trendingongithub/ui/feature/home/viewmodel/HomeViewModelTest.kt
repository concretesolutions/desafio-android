package dev.theuzfaleiro.trendingongithub.ui.feature.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import dev.theuzfaleiro.trendingongithub.ui.feature.home.datasource.RepositoryDataSourceFactory
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Repository
import dev.theuzfaleiro.trendingongithub.ui.feature.home.repository.HomeRepository
import io.github.plastix.rxschedulerrule.RxSchedulerRule
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

private const val ERROR = 2

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
    fun shouldDisplayAnError_WhenNoRepositoriesWereFetched() {
        //arrange
        every {
            homeRepository.fetchTrendingRepositories()
        } returns Observable.just(repositoryPagedList)

        //act
        homeViewModel.fetchRepositories()

        //assert
        homeViewModel.getRepositories().value shouldBe null
        homeViewModel.getLoading().value shouldBe ERROR
    }
}