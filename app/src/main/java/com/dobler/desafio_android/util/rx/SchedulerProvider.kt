package com.dobler.desafio_android.util.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : SchedulerContract {

    override fun io() = Schedulers.io()

    override fun ui() = AndroidSchedulers.mainThread()

}