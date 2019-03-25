package dev.renatoneto.githubrepos.util.rx

import io.reactivex.Scheduler

interface SchedulerContract {

    fun io(): Scheduler

    fun ui(): Scheduler

}