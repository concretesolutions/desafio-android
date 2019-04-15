package com.dobler.desafio_android.util.rx

import io.reactivex.Scheduler

interface SchedulerContract {

    fun io(): Scheduler

    fun ui(): Scheduler
}
