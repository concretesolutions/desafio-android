package com.losingtimeapps.presentation.schedule

import com.losingtimeapps.domain.boundary.BaseScheduler
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ScheduleImp : BaseScheduler {

    override fun computation(): Scheduler = Schedulers.computation()


    override fun io(): Scheduler = Schedulers.io()


    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

}