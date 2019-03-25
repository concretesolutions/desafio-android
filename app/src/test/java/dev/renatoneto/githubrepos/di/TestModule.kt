package dev.renatoneto.githubrepos.di

import dev.renatoneto.githubrepos.network.github.GithubDataSource
import dev.renatoneto.githubrepos.network.github.GithubTestService
import dev.renatoneto.githubrepos.util.rx.SchedulerContract
import dev.renatoneto.githubrepos.util.rx.TestSchedulerProvider
import org.koin.dsl.module

object TestModule {

    val testRxModule = module {

        single {
            TestSchedulerProvider() as SchedulerContract
        }

    }

    val testDataSourceModule = module {

        single { GithubTestService() as GithubDataSource }

    }

}