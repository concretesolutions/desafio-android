package com.hako.githubapi

import android.app.Application
import com.hako.githubapi.di.database
import com.hako.githubapi.di.network
import com.hako.githubapi.di.usecases
import com.hako.githubapi.domain.requests.QueryPullRequest
import com.hako.githubapi.domain.usecases.DeleteRepositories
import com.hako.githubapi.domain.usecases.GetPullRequests
import com.hako.githubapi.domain.usecases.GetRepositories
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.with
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.get
import org.koin.test.KoinTest
import org.mockito.Mockito.mock

class TestUsecases : KoinTest {

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        startKoin(
            listOf(
                network,
                database,
                usecases
            )
        ) with mock(Application::class.java)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `is getRepository usecase working`() {
        val usecase: GetRepositories = get()
        usecase.execute(1)
    }

    @Test
    fun `is getPullRequests usecase working`() {
        val usecase: GetPullRequests = get()
        usecase.execute(QueryPullRequest("", ""))
    }

    @Test
    fun `is deleteRepositories usecase working`() {
        val usecase: DeleteRepositories = get()
        usecase.execute()
    }
}
