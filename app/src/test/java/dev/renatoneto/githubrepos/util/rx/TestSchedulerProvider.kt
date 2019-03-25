package dev.renatoneto.githubrepos.util.rx

import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerContract {

    override fun io() = Schedulers.trampoline()

    override fun ui() = Schedulers.trampoline()

}