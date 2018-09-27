package br.com.concrete.desafio.data

import timber.log.Timber

fun setupTimber(isDebug: Boolean) {
    if (isDebug)
        Timber.plant(Timber.DebugTree())
}