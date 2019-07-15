package com.pedrenrique.githubapp.core.domain

abstract class UseCase<Params, Source> {
    abstract suspend fun run(params: Params): Source

    suspend operator fun invoke(params: Params) = run(params)

    abstract class NoParam<Source> : UseCase<None, Source>() {
        abstract suspend fun run(): Source

        final override suspend fun run(params: None) =
            throw UnsupportedOperationException()

        suspend operator fun invoke(): Source = run()
    }

    object None
}